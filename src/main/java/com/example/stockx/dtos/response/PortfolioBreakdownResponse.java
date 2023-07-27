package com.example.stockx.dtos.response;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Service;

public class PortfolioBreakdownResponse {
    @SerializedName("withdrawable_cash")
    private Number withdrawableCash;
    private Number value;
    @SerializedName("total_return")
    private Number totalReturn;
    @SerializedName("total_percent_change")
    private Number totalPercentChange;
    private Number total_invested;
    private Number total_aum;
    private boolean restricted_by_dw;
    private Number number_of_violations;
    private Number equity_value;
    private String currency_symbol;
    private Number base_wallet_balance;
    private Number available_to_invest;
}
