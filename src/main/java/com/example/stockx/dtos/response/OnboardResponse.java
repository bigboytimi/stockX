package com.example.stockx.dtos.response;

import com.example.stockx.dtos.payload.OnboardActions;

import java.util.List;

public class OnboardResponse {
    private String us_stocks_status;
    private boolean funded_action;
    private List<OnboardActions> actions;
}
