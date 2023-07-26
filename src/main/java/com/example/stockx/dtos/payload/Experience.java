package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;

public class Experience {
    @SerializedName("yrs_5_10")
    private Number fiveToTenYears;
    @SerializedName("yrs_3_5")
    private Number threeToFiveYears;
    @SerializedName("yrs_1_2")
    private Number oneToTwoYears;
    @SerializedName("yrs_10_")
    private Number overTenYears;
    @SerializedName("none")
    private Number zeroYears;
}
