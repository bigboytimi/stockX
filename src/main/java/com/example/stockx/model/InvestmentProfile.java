package com.example.stockx.model;

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
    private int yearly_income;
    private String source_of_wealth;
    private String risk_tolerance;
    private String position;
    private String net_worth;
    private String marital_status;
    private int liquid;
    private String goal;
    private String experience;
    private String employment_type;
    private String getEmployment_status;
    private int dependents;
    private String company;

}
