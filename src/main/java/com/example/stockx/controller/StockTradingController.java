package com.example.stockx.controller;

import com.example.stockx.dtos.request.CalculateOrderRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.PlaceOrderRequest;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.stocktrading.StockTradingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockTradingController {
    private final StockTradingUseCase stockTradingUseCase;

    @GetMapping("/list-stocks")
    public ResponseEntity<GlobalResponse<StocksResponse>> getListOfStock(@RequestParam(name = "limit") String limit,
                                                                         @RequestParam(name = "next_token") Integer nextToken) {
        GlobalResponse<StocksResponse> response = new GlobalResponse<>(stockTradingUseCase.getListOfStocks(limit, nextToken));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search-stocks")
    public ResponseEntity<GlobalResponse<StockSearchResponse>> getListOfStock(@RequestParam(name = "query") String query,
                                                                              @RequestParam(name = "theme_id") String theme_id,
                                                                              @RequestParam(name = "filters") String filters,
                                                                              @RequestParam(name = "sort") String sort) {
        GlobalResponse<StockSearchResponse> response = new GlobalResponse<>(stockTradingUseCase.searchStocks(query, theme_id, filters, sort));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/calculate-order")
    public ResponseEntity<GlobalResponse<OrderResponse>> calculateOrder(@RequestBody CalculateOrderRequest request) throws InvalidRequestException {
        GlobalResponse<OrderResponse> response = new GlobalResponse<>(stockTradingUseCase.processCalculateOrder(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/place-order")
    public ResponseEntity<GlobalResponse<OrderIdResponse>> placeOrder(@RequestBody PlaceOrderRequest request) throws InvalidRequestException {
        GlobalResponse<OrderIdResponse> response = new GlobalResponse<>(stockTradingUseCase.processPlaceOrder(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/order-status/{order_id}")
    public ResponseEntity<GlobalResponse<OrderStatusResponse>> getOrderStatus(@RequestBody HeaderRequest request,
                                                                              @PathVariable("order_id") String id) throws InvalidRequestException {
        GlobalResponse<OrderStatusResponse> response = new GlobalResponse<>(stockTradingUseCase.getOrderStatus(request, id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pending-orders")
    public ResponseEntity<GlobalResponse<PendingOrdersResponse>> getPendingOrders(@RequestBody HeaderRequest request) throws InvalidRequestException {
        GlobalResponse<PendingOrdersResponse> response = new GlobalResponse<>(stockTradingUseCase.processPendingOrders(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
