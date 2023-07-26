package com.example.stockx.service;

import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface StockTradingService {
    public String register(String clientToken, String request);
    public String verifyNumber(String clientToken, String request);
    public String createProfile(String clientToken, String requestBody);
    public CustomResponse verifyIdentity(String clientToken, String requestBody);
    public CustomResponse createAffiliation(String clientToken, String requestBody);
    public LoginResponse loginUser(String requestBody);
}

