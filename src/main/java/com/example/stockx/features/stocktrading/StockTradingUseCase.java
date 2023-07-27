package com.example.stockx.features.stocktrading;

import com.example.stockx.dtos.request.CalculateOrderRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.PlaceOrderRequest;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;

public interface StockTradingUseCase {
    public StocksResponse getListOfStocks(String limit, Integer nextToken);
    public StockSearchResponse searchStocks(String query, String themeId, String filters, String sort);
    public OrderResponse processCalculateOrder(CalculateOrderRequest request) throws InvalidRequestException;
    OrderIdResponse processPlaceOrder(PlaceOrderRequest request) throws InvalidRequestException;

    public OrderStatusResponse getOrderStatus(HeaderRequest request, String id) throws InvalidRequestException;

    public PendingOrdersResponse processPendingOrders(HeaderRequest request) throws InvalidRequestException;

}
