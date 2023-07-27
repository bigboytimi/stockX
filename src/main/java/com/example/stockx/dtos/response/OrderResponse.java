package com.example.stockx.dtos.response;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    private int fee;
    @SerializedName("order_price")
    private Number orderPrice;
    @SerializedName("total_price")
    private Number totalPrice;
    @SerializedName("number_of_violations")
    private int numberOfViolations;
    @SerializedName("gfv_occurs")
    private boolean gfvOccurs;
    @SerializedName("currency_symbol")
    private String currencySymbol;

    @SerializedName("blocked_quantity")
    private Number blockedQuantity;
    private Number quantity;
    @SerializedName("price_per_share")
    private Number pricePerShare;
}
