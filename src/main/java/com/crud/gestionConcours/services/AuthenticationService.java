package com.crud.gestionconcours.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.gestionconcours.repository.ConcoursRepository;
import com.crud.gestionconcours.repository.UserRepository;


@Service
public class AuthenticationService {


    private ConcoursRepository concoursRepository = null;



    @Autowired
    public AuthenticationService(
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            ConcoursRepository candidatureRepository,
            AuthenticationManager authenticationManager) {


        this.concoursRepository = concoursRepository;

    }

}
