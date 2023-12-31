package com.example.stockx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public ResponseEntity<String> deposit(String clientToken, String requestSource, String request);

    public ResponseEntity<String> getAccount(String clientToken, String requestSource, Integer userId);

    public ResponseEntity<String> fetchDeposits(String clientToken, String requestSource, String limit, Integer nextToken, String startDate, String endDate);
}
