package com.crud.gestionconcours.services;


import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.crud.gestionconcours.model.User;
import com.crud.gestionconcours.repository.UserRepository;



@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    /**
     * This method allow us to load user from database by this username
     * 
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Créez une liste des autorités en fonction du rôle
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(String.valueOf(user.getRole())));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
