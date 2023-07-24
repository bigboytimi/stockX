package com.example.stockx.features.authorization.impl;

import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.dtos.response.TokenResponse;
import com.example.stockx.features.authorization.OauthUseCase;
import com.example.stockx.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthUseCaseImpl implements OauthUseCase {
    private final AuthService authService;
    @Override
    public TokenResponse authorizeUser(TokenRequest tokenRequest) {
        return authService.authorize(tokenRequest);
    }
}
