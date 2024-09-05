package com.crud.gestionconcours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestionconcours.model.Concours;

public interface ConcoursRepository extends JpaRepository<Concours, Integer>{
    
}
