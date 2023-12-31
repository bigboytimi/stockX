package com.example.stockx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    public String register(String clientToken, String request);
    public ResponseEntity<String> verifyNumber(String clientToken, String request);
    public ResponseEntity<String> createProfile(String clientToken, String requestBody);
    public ResponseEntity<String> verifyIdentity(String clientToken, String requestBody);
    public ResponseEntity<String> createAffiliation(String clientToken, String requestBody);
    public ResponseEntity<String> loginUser(String requestBody);
    public ResponseEntity<String> refreshToken(String requestBody);
}
