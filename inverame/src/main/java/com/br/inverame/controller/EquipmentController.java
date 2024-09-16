package com.br.inverame.controller;

import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.dto.EquipmentDTO;
import com.br.inverame.repository.ClientRepository;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.service.EquipmentService;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("image") MultipartFile file,
            @RequestParam("osNumber") int osNumber,
            @RequestParam("name") String name,
            @RequestParam(value = "responsiblePerson", required = false) String responsiblePerson,
            @RequestParam("serialNumber") String serialNumber,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("current") int current,
            @RequestParam("power") int power,
            @RequestParam("voltage") int voltage,
            @RequestParam("priorityLevel") String priorityLevel,
            @RequestParam(value = "lastOS", required = false) String lastOS,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "codClient", required = false) String codClient) throws IOException, GeneralSecurityException {
    
        // Verifica se o arquivo de imagem está vazio
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo está vazio");
        }
    
        // Verifica se já existe um equipamento com o mesmo número de série
        if (equipmentRepository.existsBySerialNumber(serialNumber)) {
            return ResponseEntity.badRequest().body("Já existe um equipamento com o mesmo número de série.");
        }
    
        // Verifica se já existe um equipamento com o mesmo número da OS
        if (equipmentRepository.existsByOsNumber(osNumber)) {
            return ResponseEntity.badRequest().body("Já existe um equipamento com o mesmo número da OS.");
        }
    
        // Verifica se o cliente existe se codClient for fornecido
        Client client = null;
        if (codClient != null && !codClient.isEmpty()) {
            Optional<Client> optionalClient = clientRepository.findByCodClient(codClient);
            if (optionalClient.isPresent()) {
                client = optionalClient.get();
            } else {
                return ResponseEntity.badRequest().body("Cliente não encontrado");
            }
        }
    
        // Salvar o arquivo temporariamente
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
    
        // Tenta fazer o upload da imagem para o Google Drive
        String imageUrl = equipmentService.uploadImageToDrive(tempFile, serialNumber, name);
        if (imageUrl == null) {
            // Se falhar, remove o arquivo temporário e retorna erro
            tempFile.delete();
            return ResponseEntity.status(500).body("Erro ao enviar imagem para o Google Drive");
        }
    
        // Criar e salvar o equipamento com os dados fornecidos
        Equipment equipment = new Equipment();
        equipment.setOsNumber(osNumber);
        equipment.setName(name);
        equipment.setResponsiblePerson(responsiblePerson);
        equipment.setSerialNumber(serialNumber);
        equipment.setBrand(brand);
        equipment.setModel(model);
        equipment.setCurrent(current);
        equipment.setPower(power);
        equipment.setVoltage(voltage);
        equipment.setPriorityLevel(priorityLevel);
        equipment.setLastOS(lastOS);
        equipment.setDescription(description);
        equipment.setPhotoURL(imageUrl);
        equipment.setRegistrationDate(LocalDateTime.now());
        equipment.setClient(client); // O cliente será null se não for fornecido
    
        equipmentRepository.save(equipment);
    
        // Remove o arquivo temporário após o sucesso
        tempFile.delete();
    
        return ResponseEntity.ok("Equipamento salvo com sucesso");
    }
    
    // ------------------------------Thailan--------------------------------

    // =============================LISTAR POR ID======================

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> equipmentOpt = equipmentRepository.findById(id);
        if (equipmentOpt.isPresent()) {
            EquipmentDTO equipmentDTO = new EquipmentDTO(equipmentOpt.get());
            return ResponseEntity.ok(equipmentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // =============================LISTAR TODOS ======================

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentDTO> equipmentDTOs = equipments.stream()
                .map(EquipmentDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(equipmentDTOs);
    }

    // ================================ATUALIZAR POR ID=======================
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Long id,
            @RequestBody Equipment equipmentDetails) {
        return equipmentRepository.findById(id)
                .map(equipment -> {
                    equipment.setOsNumber(equipmentDetails.getOsNumber());
                    equipment.setName(equipmentDetails.getName());
                    equipment.setResponsiblePerson(equipmentDetails.getResponsiblePerson());
                    equipment.setSerialNumber(equipmentDetails.getSerialNumber());
                    equipment.setBrand(equipmentDetails.getBrand());
                    equipment.setModel(equipmentDetails.getModel());
                    equipment.setCurrent(equipmentDetails.getCurrent());
                    equipment.setPower(equipmentDetails.getPower());
                    equipment.setVoltage(equipmentDetails.getVoltage());
                    equipment.setPriorityLevel(equipmentDetails.getPriorityLevel());
                    equipment.setLastOS(equipmentDetails.getLastOS());
                    equipment.setDescription(equipmentDetails.getDescription());
                    equipment.setPhotoURL(equipmentDetails.getPhotoURL());
                    equipment.setRegistrationDate(equipmentDetails.getRegistrationDate());

                    if (equipmentDetails.getClient() != null) {
                        Client client = clientRepository.findByCodClient(equipmentDetails.getClient().getCodClient())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                        equipment.setClient(client);
                    }

                    Equipment updatedEquipment = equipmentRepository.save(equipment);
                    return ResponseEntity.ok(updatedEquipment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // =====================================DELETE=======================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        if (equipmentRepository.existsById(id)) {
            equipmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
