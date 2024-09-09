package com.crud.gestionconcours.services;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    // service pour gerer le televersement et le stockage de fichiers photos

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        // Crée le chemin complet pour le fichier
        Path path = Paths.get(uploadDir, file.getOriginalFilename());
        
        // Écrit le fichier dans le système de fichiers
        Files.write(path, file.getBytes());
        
        // Retourne l'URL ou le chemin du fichier
        return path.toString();
    }
}
