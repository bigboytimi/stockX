package com.example.stockx.features.portfolio;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.PortfolioBreakdownResponse;
import com.example.stockx.exception.InvalidRequestException;

public interface PortfolioUseCase {
    public PortfolioBreakdownResponse getPortfolioBreakdown(HeaderRequest request) throws InvalidRequestException;
}
