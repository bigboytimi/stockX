package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.OrderToCalculate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateOrderRequest {
    @NotBlank(message = "Invalid: headerRequest cannot be blank")
    private HeaderRequest headerRequest;
    private OrderToCalculate request;
}
