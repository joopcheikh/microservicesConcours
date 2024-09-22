package com.crud.gestionconcours.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide");
        }

        Path path = Paths.get(uploadDir).resolve(file.getOriginalFilename());

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return path.toString();
    }

    public String storeFileWithNewName(MultipartFile file, String newFileName) throws IOException {
        Path targetLocation = Paths.get(uploadDir).resolve(newFileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return targetLocation.toString();
    }
}
