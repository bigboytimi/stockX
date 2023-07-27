package com.example.stockx.features.onboard.impl;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.OnboardResponse;
import com.example.stockx.dtos.response.WithdrawStatusResponse;
import com.example.stockx.exception.VerificationFailedException;
import com.example.stockx.features.onboard.OnboardUseCase;
import com.example.stockx.service.StockTradingService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
@RequiredArgsConstructor
public class OnboardImpl implements OnboardUseCase {
    private final Gson gson;
    private final StockTradingService stockTradingService;
    @Override
    public OnboardResponse getOnboardActions(HeaderRequest request) {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();

        Type responseType = new TypeToken<WithdrawStatusResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getActions(clientToken, requestSource);

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else{
            throw new VerificationFailedException("Invalid or expired token");
        }
    }
}
