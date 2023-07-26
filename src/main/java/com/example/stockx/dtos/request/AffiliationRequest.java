package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.Affiliation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AffiliationRequest {
    private String clientToken;
    private Affiliation data;
}
