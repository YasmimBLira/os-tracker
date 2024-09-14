package com.br.inverame.service;

import com.br.inverame.config.Res;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class EquipmentService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACOUNT_KEY_PATH = getPathToGoodleCredentials();

    private static String getPathToGoodleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "os-tracker/credentials.json");
        return filePath.toString();
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
            e.printStackTrace(); // Adiciona mais detalhes ao stack trace
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

    // Método auxiliar para obter a extensão do arquivo
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        }
        return ""; // Retorna vazio se não houver extensão
    }
}
