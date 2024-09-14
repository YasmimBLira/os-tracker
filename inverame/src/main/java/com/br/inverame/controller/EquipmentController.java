package com.br.inverame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            @RequestParam("clientId") Long clientId  // Alterado para Long
    ) throws IOException, GeneralSecurityException {
    
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
    
        // Cria um arquivo temporário para salvar o upload
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
    
        // Faz o upload da imagem e obtém a URL (passando o serialNumber e name)
        String imageUrl = equipmentService.uploadImageToDrive(tempFile, serialNumber, name);
        
        if (imageUrl == null) {
            return ResponseEntity.status(500).body("Error uploading image to Google Drive");
        }
    
        // Busca o cliente a partir do ID
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    
        // Cria o objeto Equipment e preenche os dados
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
        equipment.setPhotoURL(imageUrl); // Seta a URL da imagem no campo photoURL
        equipment.setRegistrationDate(LocalDateTime.now());
        equipment.setClientId(client); // Associa o cliente ao equipamento
    
        // Salva o Equipment no banco de dados
        equipmentRepository.save(equipment);
    
        return ResponseEntity.ok("Equipamento salvo com sucesso");
    }
    
}