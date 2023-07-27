package com.example.stockx.dtos.response;

import com.example.stockx.dtos.payload.DepositDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepositsResponse {
    private int next_token;
    private List<DepositDetails> deposits;
}
