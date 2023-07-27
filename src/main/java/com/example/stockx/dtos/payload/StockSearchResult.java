package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;

public class StockSearchResult {
    @SerializedName("value_change")
    private Number valueChange;
    @SerializedName("user_equity")
    private Number userEquity;
    @SerializedName("total_return")
    private Number totalReturn;
    private String symbol;
    private Number quantity;
    private String name;
    private String logo;
    private String favourite;
    private String avatar;
}
