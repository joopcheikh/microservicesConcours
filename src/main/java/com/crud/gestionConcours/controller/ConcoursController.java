package com.crud.gestionconcours.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.gestionconcours.model.Concours;
import com.crud.gestionconcours.services.ConcoursServiceImpl;
import com.crud.gestionconcours.services.FileStorageService;

@RestController
public class ConcoursController {

    @Autowired
    public ConcoursServiceImpl concoursServiceImpl;

    @Autowired
    private FileStorageService fileStorageService;

   @PostMapping("/add/concours")
public ResponseEntity<Concours> addConcours(
        @RequestParam("file") MultipartFile file,
        @RequestParam("nom") String nom,
        @RequestParam("date") String date,
        @RequestParam("criteres") String criteres,
        @RequestParam("description") String description) {

    try {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uniqueFileName = UUID.randomUUID().toString() + fileExtension;
        String photoUrl = fileStorageService.storeFileWithNewName(file, uniqueFileName);
        Concours concours = new Concours();
        concours.setNom(nom);
        concours.setDate(java.sql.Date.valueOf(date));
        concours.setDescription(description);
        concours.setPhotoUrl(photoUrl);
        concours.setCriteres(criteres);
        return ResponseEntity.ok(concoursServiceImpl.addConcours(concours));
    } catch (IOException e) {
        return ResponseEntity.status(500).body(null);
    }
}

    @PutMapping("/change/concours")
    public ResponseEntity<Concours> changeConcours(@RequestBody Concours concours) {
        Concours updatedConcours = concoursServiceImpl.changeConcours(concours);
        if (updatedConcours != null) {
            return ResponseEntity.ok(updatedConcours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/concours/{id}")
    public ResponseEntity<Void> deleteConcour(@PathVariable Integer id) {
        boolean isDeleted = concoursServiceImpl.deleteConcour(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build(); 
        }
    }

    @GetMapping("/all-concours")
    public List<Concours> getAllConcours() {
        return concoursServiceImpl.getAllConcours();
    }

}
