package com.example.stockx.features.stocktrading.impl;

import com.example.stockx.dtos.payload.OrderToCalculate;
import com.example.stockx.dtos.payload.PlaceOrder;
import com.example.stockx.dtos.request.CalculateOrderRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.PlaceOrderRequest;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;
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

    @Override
    public StockSearchResponse searchStocks(String query, String themeId, String filters, String sort) {
        Type responseType = new TypeToken<StockSearchResponse>(){}.getType();
        ResponseEntity<String> apiResponse = stockTradingService.getSearchedStocks(query, themeId, filters, sort);
        String responseBody = apiResponse.getBody();

        if(apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }


    }

    @Override
    public OrderResponse processCalculateOrder(CalculateOrderRequest request) throws InvalidRequestException {
        String clientToken = request.getHeaderRequest().getClientToken();
        String requestSource = request.getHeaderRequest().getRequestSource();
        String currency = request.getHeaderRequest().getCurrency();
        OrderToCalculate calculateOrderData = request.getRequest();

        if (currency == null || requestSource == null ){
            throw new InvalidRequestException("currency or requestSource must not be null");
        }

        String requestBody = gson.toJson(calculateOrderData);
        Type responseType = new TypeToken<OrderResponse>() {
        }.getType();
        ResponseEntity<String> apiResponse = stockTradingService.calculateOrder(requestBody, clientToken, requestSource, currency);

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


    @Override
    public OrderIdResponse processPlaceOrder(PlaceOrderRequest request) throws InvalidRequestException {
        String clientToken = request.getHeaderRequest().getClientToken();
        String requestSource = request.getHeaderRequest().getRequestSource();
        String currency = request.getHeaderRequest().getCurrency();
        PlaceOrder placeOrderData = request.getRequestData();

        if (currency == null || requestSource == null ){
            throw new InvalidRequestException("Client token or currency must not be null");
        }

        String requestBody = gson.toJson(placeOrderData);

        Type responseType = new TypeToken<OrderResponse>() {}.getType();
        ResponseEntity<String> apiResponse = stockTradingService.placeOrder(requestBody, clientToken, requestSource, currency);
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

    @Override
    public OrderStatusResponse getOrderStatus(HeaderRequest request, String id) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();

        if (requestSource == null ){
            throw new InvalidRequestException("Request Source must not be null");
        }

        Type responseType = new TypeToken<OrderStatusResponse>() {}.getType();
        ResponseEntity<String> apiResponse = stockTradingService.processOrderStatus(clientToken, requestSource, id);
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

    @Override
    public PendingOrdersResponse processPendingOrders(HeaderRequest request) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        String requestSource = request.getRequestSource();
        String currency = request.getCurrency();

        if (currency == null || requestSource == null ){
            throw new InvalidRequestException("currency or requestSource must not be null");
        }
        Type responseType = new TypeToken<PendingOrdersResponse>() {}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getPendingOrders(clientToken, requestSource, currency);

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
