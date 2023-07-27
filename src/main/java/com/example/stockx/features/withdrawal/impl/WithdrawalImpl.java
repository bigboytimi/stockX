package com.example.stockx.features.withdrawal.impl;

import com.example.stockx.dtos.payload.NairaWithdrawal;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.NairaWithdrawalRequest;
import com.example.stockx.dtos.response.WithdrawStatusResponse;
import com.example.stockx.exception.VerificationFailedException;
import com.example.stockx.features.withdrawal.WithdrawalUseCase;
import com.example.stockx.service.StockTradingService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
@RequiredArgsConstructor
public class WithdrawalImpl implements WithdrawalUseCase {
    private final Gson gson;
    private final StockTradingService stockTradingService;
    @Override
    public String withdrawNaira(NairaWithdrawalRequest request) {
       String clientToken = request.getHeaderRequest().getClientToken();
       NairaWithdrawal nairaWithdrawalRequest = request.getRequest();

       String requestBody = gson.toJson(nairaWithdrawalRequest);

       ResponseEntity<String> apiResponse = stockTradingService.withdraw(clientToken, requestBody);

       if (apiResponse.getStatusCode().is2xxSuccessful()){
           return "Withdraw created";
       } else{
           return "Request Failed. Please verify token is valid and not expired";
       }
    }

    @Override
    public WithdrawStatusResponse getWithdrawalStatus(String reference, HeaderRequest request) {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();

        Type responseType = new TypeToken<WithdrawStatusResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getStatus(reference, clientToken, requestSource);

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else {
            throw new VerificationFailedException("Invalid or Expired Token");
        }

    }
}
