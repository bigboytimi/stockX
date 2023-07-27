package com.example.stockx.dtos.payload;

import jakarta.validation.constraints.NotBlank;

public class OrderToCalculate {
    @NotBlank(message = "Invalid: type cannot be blank")
    private String type;
    @NotBlank(message = "Invalid: Symbol cannot be blank")
    private String symbol;
    @NotBlank(message = "Invalid: amount cannot be blank")
    private Number amount;
    @NotBlank(message = "Invalid: price cannot be blank")
    private Number price;
    @NotBlank(message = "Invalid: price_per_share cannot be blank")
    private Number price_per_share;
    @NotBlank(message = "Invalid: side cannot be blank")
    private String side;
}
