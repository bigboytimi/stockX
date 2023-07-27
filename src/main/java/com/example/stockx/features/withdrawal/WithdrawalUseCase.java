package com.example.stockx.features.withdrawal;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.NairaWithdrawalRequest;
import com.example.stockx.dtos.response.WithdrawStatusResponse;

public interface WithdrawalUseCase {
    public String withdrawNaira(NairaWithdrawalRequest request);

    public WithdrawStatusResponse getWithdrawalStatus(String reference, HeaderRequest request);
}
