package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;

public class DepositDetails {
    @SerializedName("user_surname")
    private String userSurname;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_id")
    private int userId;
    private String txref;
    private String status;
    @SerializedName("phone_number")
    private String phoneNumber;
    private int id;
    private String email;
    @SerializedName("created_at")
    private int dateCreated;
    @SerializedName("api_consumer_name")
    private String clientName;
    @SerializedName("api_consumer_id")
    private int clientId;
    private String account;
}
