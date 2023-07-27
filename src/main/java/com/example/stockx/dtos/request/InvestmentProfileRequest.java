package com.example.stockx.dtos.request;

import com.example.stockx.model.InvestmentProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class InvestmentProfileRequest {
        private String clientToken;
        private InvestmentProfile request;
}
