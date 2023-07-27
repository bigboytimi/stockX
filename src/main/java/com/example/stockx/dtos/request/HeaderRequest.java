package com.example.stockx.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderRequest {
    @NotBlank(message = "Invalid: client token must not be blank")
    private String clientToken;

    private String requestSource;
    private String currency;
}
