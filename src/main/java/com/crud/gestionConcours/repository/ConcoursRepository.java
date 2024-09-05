package com.crud.gestionConcours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestionConcours.model.Concours;

public interface ConcoursRepository extends JpaRepository<Concours, Integer>{
    
}
