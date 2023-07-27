package com.example.stockx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileService {
    public ResponseEntity<String> getClientDetails();
    public ResponseEntity<String> updateUserInfo(String requestBody);
    public ResponseEntity<String> updatePassword(String requestBody);
    public ResponseEntity<String> getInvestmentDetails();
}
