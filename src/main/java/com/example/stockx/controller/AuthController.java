package com.example.stockx.controller;

import com.example.stockx.dtos.response.TokenResponse;
import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.features.authorization.OauthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OauthUseCase oauthUseCase;

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getClientToken(@RequestBody TokenRequest request){
        return new ResponseEntity<>(oauthUseCase.authorizeUser(request), HttpStatus.OK);
    }


}
