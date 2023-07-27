package com.example.stockx.dtos.response;

import com.example.stockx.dtos.payload.Stock;

import java.util.List;

public class StocksResponse {
    private List<Stock> stocks;
    private Integer next_token;
}
