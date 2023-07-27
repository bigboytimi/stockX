package com.example.stockx.features.stocktrading;

import com.example.stockx.dtos.response.StocksResponse;

public interface StockTradingUseCase {
    public StocksResponse getListOfStocks(String limit, Integer nextToken);
}
