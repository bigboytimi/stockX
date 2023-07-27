package com.example.stockx.features.profile_mgmt.deposit.impl;

import com.example.stockx.dtos.payload.Deposit;
import com.example.stockx.dtos.request.DepositRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.AccountDetailsResponse;
import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.DepositResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.profile_mgmt.deposit.AccountUseCase;
import com.example.stockx.service.AccountService;
import com.example.stockx.utils.ResponseParserUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountUseCaseImpl implements AccountUseCase {
    private AccountService accountService;
    private final Gson gson;
    private final ResponseParserUtils parserUtils;

    @Override
    public DepositResponse depositMoney(DepositRequest request) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();
        Deposit deposit = request.getData();

        String requestBody = gson.toJson(deposit);

        Type responseType = new TypeToken<DepositResponse>() {
        }.getType();

        ResponseEntity<String> apiResponse = accountService.deposit(clientToken, requestSource, requestBody);

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public AccountDetailsResponse getAccountDetails(Integer userId, HeaderRequest request) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();
        Type responseType = new TypeToken<AccountDetailsResponse>() {
        }.getType();

        ResponseEntity<String> apiResponse = accountService.getAccount(clientToken, requestSource, userId);

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }


}


