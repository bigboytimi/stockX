package com.example.stockx.service;

import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.dtos.response.TokenResponse;

public interface AuthService {
    public TokenResponse authorize(TokenRequest tokenRequest);
}
