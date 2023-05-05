package com.sang.sangschoolback.controller;


import com.sang.sangschoolback.controller.dto.AuthDto;

import com.sang.sangschoolback.controller.dto.TokenDto;
import com.sang.sangschoolback.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public TokenDto authenticate(@NonNull HttpServletRequest req, @RequestBody AuthDto authDto
    ){
        return authenticationService.authenticate(authDto);
    }

}
