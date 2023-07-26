package com.example.stockx.service.impl;

import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.dtos.response.TokenResponse;
import com.example.stockx.service.ApiConnection;
import com.example.stockx.service.AuthService;
import com.example.stockx.service.StockTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BambooServiceImpl implements AuthService, StockTradingService {
    private final ApiConnection apiConnection;

    @Value("${api.secret-key}")
    private String appKey;
    @Override
    public TokenResponse authorize(TokenRequest tokenRequest) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("app-key", appKey);

        return apiConnection.connectAndPost(headers, tokenRequest, url, HttpMethod.POST, TokenResponse.class);
    }

    @Override
    public String register(String clientToken, String request) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/register";
        HttpHeaders headers = setHeaders(clientToken);
        return apiConnection.connectAndPost(headers, request, url, HttpMethod.POST, String.class);
    }

    @Override
    public ResponseEntity<String> verifyNumber(String clientToken, String request) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/verify_phone_number";
        HttpHeaders headers = setHeaders(clientToken);
        return apiConnection.postAndGetResponseEntity(headers, request, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> createProfile(String clientToken, String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/investment_profile";
        HttpHeaders headers = setHeaders(clientToken);
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> verifyIdentity(String clientToken, String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/verify_identity_identifier";
        HttpHeaders headers = setHeaders(clientToken);
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> createAffiliation(String clientToken, String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/affiliations";
        HttpHeaders headers = setHeaders(clientToken);
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> loginUser(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/login";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> refreshToken(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/refresh_token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> getClientDetails() {
       String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/profile";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        return apiConnection.connectAndGet(headers, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> updateUserInfo(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/profile";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.PATCH);
    }

    @Override
    public ResponseEntity<String> updatePassword(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/profile/password";
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.PATCH);
    }

    public static HttpHeaders setHeaders(String clientToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        headers.set("x_client_token", clientToken);
        return headers;
    }
}
