package com.br.inverame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.Equipment;
import com.br.inverame.repository.ClientRepository;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.service.EquipmentService;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;

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
        @RequestParam("responsiblePerson") String responsiblePerson,
        @RequestParam("serialNumber") String serialNumber,
        @RequestParam("brand") String brand,
        @RequestParam("model") String model,
        @RequestParam("current") int current,
        @RequestParam("power") int power,
        @RequestParam("voltage") int voltage,
        @RequestParam("priorityLevel") String priorityLevel,
        @RequestParam("lastOS") String lastOS,
        @RequestParam("description") String description,
        @RequestParam("clientId") Long clientId) throws IOException, GeneralSecurityException {

    // Verifica se um equipamento com o mesmo número de série já existe
    if (equipmentRepository.existsBySerialNumber(serialNumber)) {
        return ResponseEntity.badRequest().body("Já existe um equipamento com o mesmo número de série.");
    }

    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("O arquivo está vazio");
    }

    File tempFile = File.createTempFile("temp", null);
    file.transferTo(tempFile);

    String imageUrl = equipmentService.uploadImageToDrive(tempFile, serialNumber, name);
    if (imageUrl == null) {
        return ResponseEntity.status(500).body("Erro ao enviar imagem para o Google Drive");
    }

    Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

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
    equipment.setClient(client);

    equipmentRepository.save(equipment);
    return ResponseEntity.ok("Equipamento salvo com sucesso");
}


    // ------------------------------Thailan--------------------------------

    // =============================LISTAR POR ID======================

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return equipmentRepository.findById(id)
                .map(equipment -> ResponseEntity.ok().body(equipment))
                .orElse(ResponseEntity.notFound().build());
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
                    equipment.setClient(equipmentDetails.getClient());
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
