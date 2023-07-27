package com.example.stockx.features.account_mgmt;

import com.example.stockx.dtos.request.DepositRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.AccountDetailsResponse;
import com.example.stockx.dtos.response.DepositLinkResponse;
import com.example.stockx.dtos.response.DepositsResponse;
import com.example.stockx.exception.InvalidRequestException;

public interface AccountUseCase {
    public DepositLinkResponse depositMoney(DepositRequest request) throws InvalidRequestException;

    public AccountDetailsResponse getAccountDetails(Integer userId, HeaderRequest request) throws InvalidRequestException;

    public DepositsResponse getDeposits(HeaderRequest request, String limit, Integer nextToken, String startDate, String endDate);

}
