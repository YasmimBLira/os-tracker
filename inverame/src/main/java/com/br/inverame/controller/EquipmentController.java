package com.br.inverame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.enums.Priority;
// import com.br.inverame.model.entity.dto.EquipmentDTO;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.service.EquipmentService;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("image") MultipartFile file,
            @RequestParam("equipmentName") String equipmentName,
            @RequestParam("serialNumber") String serialNumber,
            @RequestParam("carrier") String carrier,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("current") int current,
            @RequestParam("power") int power,
            @RequestParam("voltage") int voltage,
            @RequestParam("priority") Priority priority,
            @RequestParam(value = "description", required = false) String description) throws IOException, GeneralSecurityException {
    
        // Verifica se o arquivo de imagem está vazio
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("O arquivo está vazio");
        }
    
        // Verifica se já existe um equipamento com o mesmo número de série
        if (equipmentRepository.existsBySerialNumber(serialNumber)) {
            return ResponseEntity.badRequest().body("Já existe um equipamento com o mesmo número de série.");
        }
    
    
        // Salvar o arquivo temporariamente
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
    
        // Tenta fazer o upload da imagem para o Google Drive
        String imageUrl = equipmentService.uploadImageToDrive(tempFile, serialNumber, equipmentName);
        if (imageUrl == null) {
            // Se falhar, remove o arquivo temporário e retorna erro
            tempFile.delete();
            return ResponseEntity.status(500).body("Erro ao enviar imagem para o Google Drive");
        }
    
        // Criar e salvar o equipamento com os dados fornecidos
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentName);
        equipment.setSerialNumber(serialNumber);
        equipment.setCarrier(carrier);
        equipment.setBrand(brand);
        equipment.setModel(model);
        equipment.setCurrent(current);
        equipment.setPower(power);
        equipment.setVoltage(voltage);
        equipment.setPriority(priority);
        equipment.setDescription(description);
        equipment.setPhotoURL(imageUrl);
        equipment.setRegistrationDate(LocalDateTime.now());
    
        equipmentRepository.save(equipment);
    
        // Remove o arquivo temporário após o sucesso
        tempFile.delete();
    
        return ResponseEntity.ok("Equipamento salvo com sucesso");
    }
    
    // ------------------------------Thailan--------------------------------

    // =============================LISTAR POR ID======================

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        return equipment.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // =============================LISTAR TODOS ======================

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return ResponseEntity.ok(equipmentList);
    }

    // ================================ATUALIZAR POR ID=======================

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(
            @PathVariable Long id,
            @RequestBody Equipment equipmentDetails) {
        return equipmentRepository.findById(id)
                .map(equipment -> {
                    equipment.setEquipmentName(equipmentDetails.getEquipmentName());
                    equipment.setSerialNumber(equipmentDetails.getSerialNumber());
                    equipment.setCarrier(equipmentDetails.getCarrier());
                    equipment.setBrand(equipmentDetails.getBrand());
                    equipment.setModel(equipmentDetails.getModel());
                    equipment.setCurrent(equipmentDetails.getCurrent());
                    equipment.setPower(equipmentDetails.getPower());
                    equipment.setVoltage(equipmentDetails.getVoltage());
                    equipment.setPriority(equipmentDetails.getPriority());
                    equipment.setDescription(equipmentDetails.getDescription());
                    equipment.setPhotoURL(equipmentDetails.getPhotoURL());
                    equipment.setRegistrationDate(equipmentDetails.getRegistrationDate());

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
