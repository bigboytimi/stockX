package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;

public class PendingOrder {
    private String type;
    private String symbol;
    private String status;
    private String side;
    private Number quantity;
    @SerializedName("price_per_share")
    private Number pricePerShare;
    @SerializedName("order_price")
    private Number orderPrice;
    @SerializedName("order_expiration_timestamp")
    private int orderExpirationTimestamp;
    private String number;
    private String name;
    private String logo;
    @SerializedName("limit_expiration")
    private String limitExpiration;
    private String id;
    @SerializedName("creation_timestamp")
    private Number orderCreatedDate;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("background_color")
    private String backgroundColor;
    @SerializedName("avg_price")
    private Number averagePrice;
    private String avatar;
}
