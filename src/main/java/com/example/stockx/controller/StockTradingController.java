package com.example.stockx.controller;

import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.StockSearchResponse;
import com.example.stockx.dtos.response.StocksResponse;
import com.example.stockx.features.stocktrading.StockTradingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StockTradingController {
    private final StockTradingUseCase stockTradingUseCase;

    @GetMapping("/stocks")
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

}
