package com.sang.sangschoolback.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class TokenDto {
    private String token;

    public String getToken() {
        return token;
    }
}
