package com.example.stockx.dtos.response;

import com.example.stockx.dtos.payload.PendingOrder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingOrdersResponse {
    @SerializedName("pending_orders")
    private List<PendingOrder> pendingOrders;
    @SerializedName("currency_symbol")
    private String currencySymbol;
}
