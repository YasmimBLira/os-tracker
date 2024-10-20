package com.br.inverame.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.nio.file.Files;

import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.dto.EquipmentUpdateDTO;
import com.br.inverame.repository.EquipmentRepository;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.drive.DriveScopes;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoogleCredentials();

    private static String getPathToGoogleCredentials() {
        return "C:\\Users\\PC\\Desktop\\os-tracker\\credentials.json";
    }

    public Equipment saveEquipment(Equipment equipment, File imageFile) throws GeneralSecurityException, IOException {
        // Verifica se um equipamento com o número de série já existe
        if (equipmentRepository.findBySerialNumber(equipment.getSerialNumber()).isPresent()) {
            throw new IllegalArgumentException("Equipment with this serial number already exists.");
        }
   
        // Define a data de registro automaticamente
        equipment.setRegistrationDate(LocalDateTime.now());

        // Faz upload da imagem e obtém a URL
        String imageUrl = uploadImageToDrive(imageFile, equipment.getSerialNumber(), equipment.getEquipmentName());
        equipment.setPhotoURL(imageUrl); // Armazena a URL da imagem no equipamento

        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(String serialNumber, EquipmentUpdateDTO equipmentUpdateDTO, File imageFile) throws GeneralSecurityException, IOException {
        Equipment existingEquipment = equipmentRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new IllegalArgumentException("Equipment with this serial number does not exist."));

        // Atualiza todos os campos permitidos
        existingEquipment.setSerialNumber(equipmentUpdateDTO.getSerialNumber());
        existingEquipment.setEquipmentName(equipmentUpdateDTO.getEquipmentName());
        existingEquipment.setCarrier(equipmentUpdateDTO.getCarrier());
        existingEquipment.setReceiver(equipmentUpdateDTO.getReceiver());
        existingEquipment.setEnterprise_name(equipmentUpdateDTO.getEnterprise_name());
        existingEquipment.setBrand(equipmentUpdateDTO.getBrand());
        existingEquipment.setModel(equipmentUpdateDTO.getModel());
        existingEquipment.setCurrent(equipmentUpdateDTO.getCurrent());
        existingEquipment.setPower(equipmentUpdateDTO.getPower());
        existingEquipment.setVoltage(equipmentUpdateDTO.getVoltage());
        existingEquipment.setPriority(equipmentUpdateDTO.getPriority());

        // Faz upload da imagem se fornecida
        if (imageFile != null) {
            String imageUrl = uploadImageToDrive(imageFile, serialNumber, existingEquipment.getEquipmentName());
            existingEquipment.setPhotoURL(imageUrl); // Atualiza a URL da imagem no equipamento
        }

        existingEquipment.setConnectors(equipmentUpdateDTO.getConnectors());
        existingEquipment.setIhm(equipmentUpdateDTO.getIhm());
        existingEquipment.setCarcass_damage(equipmentUpdateDTO.getCarcass_damage());
        existingEquipment.setEngine(equipmentUpdateDTO.getEngine());
        existingEquipment.setEngine_cables(equipmentUpdateDTO.getEngine_cables());
        existingEquipment.setFan(equipmentUpdateDTO.getFan());
        existingEquipment.setFan_carcass(equipmentUpdateDTO.getFan_carcass());
        existingEquipment.setOthers(equipmentUpdateDTO.getOthers());

        return equipmentRepository.save(existingEquipment);
    }

    public String uploadImageToDrive(File file, String serialNumber, String equipmentName) throws GeneralSecurityException, IOException {
        String imageUrl = null;
        try {
            String folderId = "1vvLR8VUzZWMa5k09XtQOTzGCENNhZqsl";
            Drive drive = createDriveService();

            // Obter extensão e MIME type do arquivo
            String mimeType = Files.probeContentType(file.toPath());
            String fileExtension = getFileExtension(file.getName());

            // Criar nome do arquivo baseado no serialNumber e equipmentName
            String customFileName = serialNumber + "_" + equipmentName.replaceAll(" ", "_") + fileExtension;

            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(customFileName);
            fileMetaData.setParents(Collections.singletonList(folderId));

            // Suporte para múltiplos formatos de imagem
            FileContent mediaContent = new FileContent(mimeType, file);

            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id").execute();

            imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error uploading image to Google Drive: " + e.getMessage(), e);
        }
        return imageUrl;
    }

    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        }
        return ""; // Retorna vazio se não houver extensão
    }

    // Método para buscar equipamento pelo número de série
    public Optional<Equipment> findBySerialNumber(String serialNumber) {
        return equipmentRepository.findBySerialNumber(serialNumber);
    }

    // Método para buscar todos os equipamentos
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    // Método para deletar equipamento pelo número de série
    public void deleteBySerialNumber(String serialNumber) {
        if (!equipmentRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new EntityNotFoundException("Equipment with this serial number does not exist.");
        }
        equipmentRepository.deleteBySerialNumber(serialNumber);
    }
}
