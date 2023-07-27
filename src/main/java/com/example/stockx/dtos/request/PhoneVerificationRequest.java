package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.PhoneVerification;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhoneVerificationRequest {
    private String clientToken;
    private PhoneVerification request;

}
