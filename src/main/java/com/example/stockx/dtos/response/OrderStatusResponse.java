package com.example.stockx.dtos.response;

import com.google.gson.annotations.SerializedName;

public class OrderStatusResponse {
    @SerializedName("update_reason")
    private String updateReason;
    private String type;
    private String symbol;
    @SerializedName("stop_price")
    private Number stopPrice;
    private String side;
    @SerializedName("quantity")
    private Number quantityOrdered;
    private Number price;
    @SerializedName("order_status")
    private String orderStatus;
    @SerializedName("name")
    private String stockName;
    @SerializedName("naira_fee")
    private float nairaFee;
    private String logo;
    @SerializedName("limit_price")
    private Number limitPrice;

    private String id;
    @SerializedName("dollar_price")
    private float dollarPrice;

    @SerializedName("dollar_fee")
    private float dollarFee;
    @SerializedName("created_when")
    private String dateCreated;
    private String commision;
    @SerializedName("background_color")
    private String backgroundColor;

    private String avatar;

}
