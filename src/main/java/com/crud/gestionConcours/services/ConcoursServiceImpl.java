package com.crud.gestionconcours.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.gestionconcours.model.Concours;
import com.crud.gestionconcours.repository.ConcoursRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConcoursServiceImpl implements ConcoursService {

    @Autowired
    private ConcoursRepository concoursRepository;
    
    @Override
    public Concours addConcours(Concours concours) {

        if(concours == null){
            log.error("concours is null");
            return null;
        }

        return concoursRepository.save(concours);
    }

    @Override
    public Concours changeConcours(Concours concours) {
    
        Optional<Concours> existingConcours = concoursRepository.findById(concours.getId());
        if (existingConcours.isPresent()) {
            Concours concoursToUpdate = existingConcours.get();
            
            // Mettre à jour les autres champs
            concoursToUpdate.setNom(concours.getNom());
            concoursToUpdate.setCandidateType(concours.getCandidateType());
            concoursToUpdate.setDossiers(concours.getDossiers());
            concoursToUpdate.setDescription(concours.getDescription());
            concoursToUpdate.setCriteres(concours.getCriteres());
            
            // Vérifier si l'URL de la photo a changé, et la mettre à jour
            if (concours.getPhotoUrl() != null) {
                concoursToUpdate.setPhotoUrl(concours.getPhotoUrl());
            }
            
            return concoursRepository.save(concoursToUpdate);
        } else {
            log.error("Le concours n'est pas trouvé");
            return null;
        }
    }
    
    public Concours getConcoursById(Integer id) {
        Optional<Concours> concours = concoursRepository.findById(id);
        return concours.orElse(null);
    }
    @Override
    public Optional<Concours> findConcourById(Integer id) {
        if (id == null) {
            log.error("id is null");
        }

        Optional<Concours> concoursFinded = concoursRepository.findById(id);
        return concoursFinded;
    }



    public List<Concours> getAllConcours() {
        return concoursRepository.findAll();
    }

     public boolean deleteConcour(Integer id) {
        Optional<Concours> concoursOptional = concoursRepository.findById(id);

        if (concoursOptional.isPresent()) {
            Concours concours = concoursOptional.get();
            String photoUrl = concours.getPhotoUrl();
            if (photoUrl != null && !photoUrl.isEmpty()) {
                try {
                    Files.deleteIfExists(Paths.get(photoUrl));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false; 
                }
            }

            concoursRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
