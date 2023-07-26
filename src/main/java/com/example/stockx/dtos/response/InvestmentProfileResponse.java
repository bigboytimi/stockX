package com.example.stockx.dtos.response;

import com.example.stockx.dtos.payload.Experience;
import com.example.stockx.dtos.payload.Goal;
import com.example.stockx.dtos.payload.RiskTolerance;
import com.example.stockx.dtos.payload.SourceOfWealth;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InvestmentProfileResponse {
    @SerializedName("source_of_wealth")
    private SourceOfWealth sourceOfWealth;
    @SerializedName("risk_tolerance")
    private RiskTolerance riskTolerance;
    private Goal goal;
    @SerializedName("experience")
    private Experience usersYearsOfExperience;
}
