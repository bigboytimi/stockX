package com.example.stockx.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponse {
    private UserResponse user;
    private String refresh_token;
    private String jwt;
}
