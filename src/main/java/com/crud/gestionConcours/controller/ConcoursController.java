package com.crud.gestionConcours.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.gestionConcours.model.Concours;
import com.crud.gestionConcours.services.ConcoursServiceImpl;

@RestController
public class ConcoursController {

    @Autowired
    public ConcoursServiceImpl concoursServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<Concours> addConcours(@RequestBody Concours concours) {
        return ResponseEntity.ok(concoursServiceImpl.addConcours(concours));
    }

    @PutMapping("/change")
    public ResponseEntity<Concours> changeConcours(@RequestBody Concours concours) {
        Concours updatedConcours = concoursServiceImpl.changeConcours(concours);
        if (updatedConcours != null) {
            return ResponseEntity.ok(updatedConcours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concours> findConcourById(@PathVariable Integer id) {
        Optional<Concours> findConcours = concoursServiceImpl.findConcourById(id);
        return ResponseEntity.of(findConcours);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcour(@PathVariable Integer id) {
        concoursServiceImpl.deleteConcour(id);
        return ResponseEntity.noContent().build();
    }
    
}
