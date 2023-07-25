package com.example.stockx.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InvestmentProfile {
    @SerializedName("yearly_income")
    private int yearlyIncome;
    @SerializedName("source_of_wealth")
    private String sourceOfWealth;
    @SerializedName("risk_tolerance")
    private String riskTolerance;
    private String position;
    @SerializedName("net_worth")
    private String netWorth;
    @SerializedName("martial_status")
    private String maritalStatus;
    private int liquid;
    private String goal;
    private String experience;
    @SerializedName("employment_type")
    private String employmentType;
    @SerializedName("employment_status")
    private String employmentStatus;
    private int dependents;
    private String company;

}
