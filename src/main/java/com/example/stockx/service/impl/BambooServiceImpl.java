package com.example.stockx.service.impl;

import com.example.stockx.dtos.request.TokenRequest;
import com.example.stockx.dtos.response.TokenResponse;
import com.example.stockx.service.AccountService;
import com.example.stockx.service.ApiConnection;
import com.example.stockx.service.AuthService;
import com.example.stockx.service.StockTradingService;
import com.example.stockx.utils.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.example.stockx.utils.HeaderUtils.setHeaders;

@Service
@RequiredArgsConstructor
public class BambooServiceImpl implements AuthService, StockTradingService, AccountService {
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
        HttpHeaders headers = setHeaders();
        return apiConnection.connectAndGet(headers, url, HttpMethod.GET);
    }

    @Override
    public ResponseEntity<String> updateUserInfo(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/profile";
        HttpHeaders headers = setHeaders();
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.PATCH);
    }

    @Override
    public ResponseEntity<String> updatePassword(String requestBody) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/profile/password";
        HttpHeaders headers = setHeaders();
        return apiConnection.postAndGetResponseEntity(headers, requestBody, url, HttpMethod.PATCH);
    }

    @Override
    public ResponseEntity<String> getInvestmentDetails() {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/investment_profile";
        HttpHeaders headers = setHeaders();
        return apiConnection.connectAndGet(headers, url, HttpMethod.GET);

    }

    @Override
    public ResponseEntity<String> getStocks(String limit, Integer nextToken) {
       String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/stocks"
               + "?limit=" + URLEncoder.encode(limit, StandardCharsets.UTF_8)
               + "&next_token" + nextToken;
        HttpHeaders headers = setHeaders();
        return apiConnection.connectAndGet(headers, url, HttpMethod.GET);
    }

    @Override
    public ResponseEntity<String> deposit(String clientToken, String requestSource, String request) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/deposit";
        HttpHeaders headers = setHeaders(clientToken, requestSource);
        return apiConnection.postAndGetResponseEntity(headers, request, url, HttpMethod.POST);
    }

    @Override
    public ResponseEntity<String> getAccount(String clientToken, String requestSource, Integer userId) {
       String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/tenant/virtual_account/" + userId;
        HttpHeaders headers = setHeaders(clientToken, requestSource);
        return apiConnection.connectAndGet(headers, url, HttpMethod.GET);
    }

    @Override
    public ResponseEntity<String> fetchDeposits(String clientToken, String requestSource, String limit, Integer nextToken, String startDate, String endDate) {
        String url = "https://powered-by-bamboo-sandbox.investbamboo.com/api/tenant/deposit"
                + "?limit=" + URLEncoder.encode(limit, StandardCharsets.UTF_8)
                + "&next_token" + nextToken
                + "&start_date" + URLEncoder.encode(startDate, StandardCharsets.UTF_8)
                + "&end_date" + URLEncoder.encode(endDate, StandardCharsets.UTF_8);
        /*
        set header parameters
         */
        HttpHeaders headers = setHeaders(clientToken, requestSource);
        return apiConnection.connectAndGet(headers, url, HttpMethod.GET);

    }


}
