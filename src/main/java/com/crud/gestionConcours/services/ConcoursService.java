package com.crud.gestionconcours.services;

import java.util.Optional;

import com.crud.gestionconcours.model.Concours;

public interface ConcoursService {

    public Concours addConcours(Concours concours);

    public Concours changeConcours(Concours concours);

    public Optional<Concours> findConcourById(Integer id);

    public void deleteConcour(Integer id);

}
