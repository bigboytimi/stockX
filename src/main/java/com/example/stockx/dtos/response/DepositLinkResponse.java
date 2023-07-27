package com.example.stockx.dtos.response;

import com.google.gson.annotations.SerializedName;

public class DepositLinkResponse {
    @SerializedName("txref")
    private String txRef;
    @SerializedName("deposit_link")
    private String depositLink;
}
