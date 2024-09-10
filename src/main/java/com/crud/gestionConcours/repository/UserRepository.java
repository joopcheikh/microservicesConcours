package com.crud.gestionconcours.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.gestionconcours.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}
