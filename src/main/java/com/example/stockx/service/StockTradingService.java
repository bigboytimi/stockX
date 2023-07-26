package com.example.stockx.service;

import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StockTradingService {
    public String register(String clientToken, String request);
    public ResponseEntity<String> verifyNumber(String clientToken, String request);
    public ResponseEntity<String> createProfile(String clientToken, String requestBody);
    public String verifyIdentity(String clientToken, String requestBody);
    public String createAffiliation(String clientToken, String requestBody);
    public ResponseEntity<String> loginUser(String requestBody);
}

