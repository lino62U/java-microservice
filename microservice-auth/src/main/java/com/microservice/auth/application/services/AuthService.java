package com.microservice.auth.application.services;


import com.microservice.auth.infrastructure.entity.ERole;
import com.microservice.auth.infrastructure.entity.PersonEntity;
import com.microservice.auth.infrastructure.entity.RoleEntity;
import com.microservice.auth.infrastructure.payload.LoginRequest;
import com.microservice.auth.infrastructure.payload.LoginResponse;
import com.microservice.auth.infrastructure.payload.RegisterRequest;
import com.microservice.auth.infrastructure.repository.PersonRepository;
import com.microservice.auth.infrastructure.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final RoleRepository roleRepository ;

    @Autowired
    private final PersonRepository userRepository;

    public LoginResponse login(@NotNull LoginRequest loginRequest) {
        Optional<PersonEntity> responseUser = userRepository.findByUserName(loginRequest.getUserName());
        //Optional<RoleEntity> responseRole = roleRepository.findAllById(responseUser.get().getId());

        if(responseUser.isPresent())
        {
            LoginResponse.builder()
                    .name(responseUser.get().getName())
                    .userName(responseUser.get().getUserName())
                    .password(responseUser.get().getPassword())
                    .build();
        }

        return new LoginResponse();
    }


    public Boolean register(@NotNull RegisterRequest request) {

        Set<RoleEntity> roles = request.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .nameRole(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        PersonEntity user = PersonEntity.builder()
                .name(request.getName())
                .userName(request.getUserName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode( request.getPassword()))
                .roles(roles)
                .build();


        userRepository.save(user);

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
//        return AuthResponse.builder()
//                .token(jwtUtils.generateJwtToken(authentication))
//                .build();
        return true;
    }


}