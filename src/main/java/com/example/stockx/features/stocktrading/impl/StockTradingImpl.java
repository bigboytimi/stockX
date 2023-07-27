package com.example.stockx.features.stocktrading.impl;

import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.SignupResponse;
import com.example.stockx.dtos.response.StocksResponse;
import com.example.stockx.exception.LoginFailedException;
import com.example.stockx.exception.VerificationFailedException;
import com.example.stockx.features.stocktrading.StockTradingUseCase;
import com.example.stockx.service.StockTradingService;
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
public class StockTradingImpl implements StockTradingUseCase {
    private final StockTradingService stockTradingService;
    private final Gson gson;
    private final ResponseParserUtils parserUtils;
    @Override
    public StocksResponse getListOfStocks(String limit, Integer nextToken) {
       Type responseType = new TypeToken<StocksResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getStocks(limit, nextToken);


        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }


    }
}
