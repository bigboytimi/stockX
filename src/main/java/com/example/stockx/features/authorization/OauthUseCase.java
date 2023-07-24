package com.example.stockx.features.authorization;

import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.dtos.response.TokenResponse;
import org.springframework.stereotype.Service;

@Service
public interface OauthUseCase {

    public TokenResponse authorizeUser(TokenRequest tokenRequest);
}
