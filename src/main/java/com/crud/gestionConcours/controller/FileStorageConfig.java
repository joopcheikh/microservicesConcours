package com.crud.gestionconcours.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FileStorageConfig {
    // Composant qui se charge de creer le dossier de stockage des photos au demarrage de l'application

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Crée le répertoire s'il n'existe pas
        }
    }
}
