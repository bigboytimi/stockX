package com.example.stockx.features.profile_mgmt.deposit;

import com.example.stockx.dtos.request.DepositRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.AccountDetailsResponse;
import com.example.stockx.dtos.response.DepositResponse;
import com.example.stockx.exception.InvalidRequestException;

public interface AccountUseCase {
    public DepositResponse depositMoney(DepositRequest request) throws InvalidRequestException;

    public AccountDetailsResponse getAccountDetails(Integer userId, HeaderRequest request) throws InvalidRequestException;

}
