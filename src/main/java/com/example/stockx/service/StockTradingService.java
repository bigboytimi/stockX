package com.example.stockx.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface StockTradingService {

    public ResponseEntity<String> getStocks(String limit, Integer nextToken);

    public ResponseEntity<String> getSearchedStocks(String query, String themeId, String filters, String sort);

    public ResponseEntity<String> calculateOrder(String requestBody, String clientToken, String requestSource, String currency);

    public ResponseEntity<String> placeOrder(String requestBody, String clientToken, String requestSource, String currency);

    public ResponseEntity<String> processOrderStatus(String clientToken, String requestSource, String id);

    public ResponseEntity<String> getPendingOrders(String clientToken, String requestSource, String currency);

    public ResponseEntity<String> getBreakdown(String clientToken, String currency);

    public ResponseEntity<String> withdraw(String clientToken, String requestBody);

    public ResponseEntity<String> getStatus(String reference, String clientToken, String requestSource);

    public ResponseEntity<String> getActions(String clientToken, String requestSource);
}

