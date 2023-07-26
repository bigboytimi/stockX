package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.Registration;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SignupRequest {

    private String clientToken;
    private Registration data;

}
