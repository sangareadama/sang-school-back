package com.sang.sangschoolback.service;

import com.sang.sangschoolback.config.JwtService;
import com.sang.sangschoolback.controller.dto.AuthDto;
import com.sang.sangschoolback.controller.dto.TokenDto;
import com.sang.sangschoolback.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public TokenDto authenticate(AuthDto authDto) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                authDto.getUsername(),
                authDto.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        var utilisateur = repository.findByUsername(authDto.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(utilisateur, utilisateur);
        return TokenDto.builder()
                .token(jwtToken)
                .build();
    }
}
