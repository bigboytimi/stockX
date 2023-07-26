package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;

public class Goal {
    @SerializedName("new")
    private Number newGoal;
    @SerializedName("long_term")
    private Number longTerm;
    private Number frequent;
    private Number infrequent;
    private Number active;
}
