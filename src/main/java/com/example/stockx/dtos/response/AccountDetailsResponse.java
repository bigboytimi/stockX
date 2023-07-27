package com.example.stockx.dtos.response;

import com.google.gson.annotations.SerializedName;

public class AccountDetailsResponse {
    @SerializedName("user_id")
    private int userId;
    private String currency;
    @SerializedName("api_consumer_id")
    private String apiConsumerId;
    private String account;
}
