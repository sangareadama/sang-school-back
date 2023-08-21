package com.sang.sangschoolback.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TokenDto {
    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
