package com.crud.gestionconcours.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.crud.gestionconcours.filter.JwtAuthenticationFilter;
import com.crud.gestionconcours.services.UserDetailsServiceImp;


@Configuration
@EnableWebSecurity

public class SpringSecurityConfig {

    private UserDetailsServiceImp userDetailsServiceImp;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SpringSecurityConfig(
            UserDetailsServiceImp userDetailsServiceImp,
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                        .requestMatchers("/all-concours").hasAnyAuthority("ADMIN", "RECRUITER", "USER")
                        .requestMatchers("/add", "/change", "/delete/{id}").hasAnyAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .userDetailsService(userDetailsServiceImp)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // allow all origins to access our service
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
                        .allowedHeaders("*");
            }
        };
    }
}