package com.sang.sangschoolback.controller.dto;



public class AuthDto {
    private String username;
    String password;

    public AuthDto() {
    }

    public AuthDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
