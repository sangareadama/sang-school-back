package com.sang.sangschoolback.controller;


import com.sang.sangschoolback.controller.dto.AuthDto;

import com.sang.sangschoolback.controller.dto.TokenDto;
import com.sang.sangschoolback.service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthenticationController {

    private final SecurityService securityService;

    public AuthenticationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/authenticate")
    public TokenDto authenticate(@RequestBody AuthDto authDto
    ){
        return securityService.autentifierUtilisateur(authDto);
    }

}
