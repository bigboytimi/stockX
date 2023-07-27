package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.PlaceOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequest {
    private HeaderRequest headerRequest;
    private PlaceOrder requestData;
}
