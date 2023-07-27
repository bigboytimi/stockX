package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.Registration;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignupRequest {

    private String clientToken;
    private Registration request;

}
