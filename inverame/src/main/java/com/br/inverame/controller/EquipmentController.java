package com.br.inverame.controller;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.dto.EquipmentUpdateDTO;
import com.br.inverame.model.enums.Choice;
import com.br.inverame.model.enums.Priority;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.service.EquipmentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository; // Injeção do repositório

    @Autowired
    private EquipmentService equipmentService;

    // Criação de equipamento
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> handleFileUpload(
            @RequestParam("image") MultipartFile file,
            @RequestParam("equipmentName") String equipmentName,
            @RequestParam("serialNumber") String serialNumber,
            @RequestParam("carrier") String carrier,
            @RequestParam("receiver") String receiver,
            @RequestParam("enterprise_name") String enterpriseName,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("current") int current,
            @RequestParam("power") int power,
            @RequestParam("voltage") int voltage,
            @RequestParam("priority") Priority priority,
            @RequestParam("connectors") Choice connectors,
            @RequestParam("ihm") Choice ihm,
            @RequestParam("carcass_damage") Choice carcassDamage,
            @RequestParam("engine") Choice engine,
            @RequestParam("engine_cables") Choice engineCables,
            @RequestParam("fan") Choice fan,
            @RequestParam("fan_carcass") Choice fanCarcass,
            @RequestParam(value = "others", required = false) String others)
            throws IOException, GeneralSecurityException {

        // Verifica se o arquivo de imagem está vazio
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "O arquivo está vazio"));
        }

        // Verifica se já existe um equipamento com o mesmo número de série
        if (equipmentRepository.existsBySerialNumber(serialNumber)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Já existe um equipamento com o mesmo número de série."));
        }

        // Salvar o arquivo temporariamente
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);

        // Tenta fazer o upload da imagem para o Google Drive
        String imageUrl = equipmentService.uploadImageToDrive(tempFile, serialNumber, equipmentName);
        if (imageUrl == null) {
            // Se falhar, remove o arquivo temporário e retorna erro
            tempFile.delete();
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao enviar imagem para o Google Drive"));
        }

        // Criar e salvar o equipamento com os dados fornecidos
        Equipment equipment = new Equipment();
        equipment.setEquipmentName(equipmentName);
        equipment.setSerialNumber(serialNumber);
        equipment.setCarrier(carrier);
        equipment.setReceiver(receiver);
        equipment.setEnterprise_name(enterpriseName); 
        equipment.setBrand(brand);
        equipment.setModel(model);
        equipment.setCurrent(current);
        equipment.setPower(power);
        equipment.setVoltage(voltage);
        equipment.setPriority(priority);
        equipment.setConnectors(connectors);
        equipment.setIhm(ihm);
        equipment.setCarcass_damage(carcassDamage);
        equipment.setEngine(engine);
        equipment.setEngine_cables(engineCables);
        equipment.setFan(fan);
        equipment.setFan_carcass(fanCarcass);
        equipment.setOthers(others);
        equipment.setPhotoURL(imageUrl);
        equipment.setRegistrationDate(LocalDateTime.now()); // Data registrada automaticamente

        equipmentRepository.save(equipment);

        // Remove o arquivo temporário após o sucesso
        tempFile.delete();

        // Retorna uma resposta estruturada
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Equipamento salvo com sucesso");
        response.put("equipmentId", equipment.getId()); // ou outro identificador relevante

        return ResponseEntity.ok(response);
    }

    // Retorna todos os equipamentos
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        List<Equipment> equipments = equipmentService.findAll();
        return new ResponseEntity<>(equipments, HttpStatus.OK);
    }

    // Buscar equipamento por número de série
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<?> getEquipmentBySerialNumber(@PathVariable String serialNumber) {
        Optional<Equipment> equipment = equipmentService.findBySerialNumber(serialNumber);
        if (equipment.isPresent()) {
            return new ResponseEntity<>(equipment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Equipment not found", HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar equipamento por número de série
    @PutMapping("/serial/{serialNumber}")
    public ResponseEntity<Map<String, Object>> updateEquipment(@PathVariable String serialNumber,
            @RequestBody EquipmentUpdateDTO equipmentUpdateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Equipment updatedEquipment = equipmentService.updateEquipment(serialNumber, equipmentUpdateDTO, null); 
            
            response.put("message", "Equipment updated successfully");
            response.put("equipmentSerialNumber", updatedEquipment.getSerialNumber());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("message", "Error updating equipment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Deletar equipamento por número de série
    @DeleteMapping("/serial/{serialNumber}")
    public ResponseEntity<Map<String, Object>> deleteEquipment(@PathVariable String serialNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            equipmentService.deleteBySerialNumber(serialNumber);
            response.put("message", "Equipment deleted successfully");
            response.put("equipmentSerialNumber", serialNumber);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
