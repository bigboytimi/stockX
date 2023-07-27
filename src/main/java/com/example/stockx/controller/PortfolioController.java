package com.example.stockx.controller;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.OrderStatusResponse;
import com.example.stockx.dtos.response.PortfolioBreakdownResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.portfolio.PortfolioUseCase;
import jakarta.xml.ws.RequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioUseCase portfolioUseCase;

    @GetMapping("/portfolio-breakdown")
    public ResponseEntity<GlobalResponse<PortfolioBreakdownResponse>> returnPortfolioBreakdown(
            @RequestBody HeaderRequest request) throws InvalidRequestException {
        GlobalResponse<PortfolioBreakdownResponse> response = new GlobalResponse<>(portfolioUseCase.getPortfolioBreakdown(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
