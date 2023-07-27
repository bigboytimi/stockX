package com.example.stockx.features.portfolio.impl;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.PendingOrdersResponse;
import com.example.stockx.dtos.response.PortfolioBreakdownResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.portfolio.PortfolioUseCase;
import com.example.stockx.service.StockTradingService;
import com.example.stockx.utils.ResponseParserUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortfolioImpl implements PortfolioUseCase {
    private final Gson gson;
    private final StockTradingService stockTradingService;
    private final ResponseParserUtils parserUtils;
    @Override
    public PortfolioBreakdownResponse getPortfolioBreakdown(HeaderRequest request) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        String currency = request.getCurrency();

        if (clientToken == null || currency == null ){
            throw new InvalidRequestException("Client token or currency must not be null");
        }

        Type responseType = new TypeToken<PortfolioBreakdownResponse>() {}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getBreakdown(clientToken, currency);

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()) {
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {
            });
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }
}
