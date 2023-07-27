package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.Deposit;
import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositRequest {
    @NotBlank(message = "Incomplete Request: Add the name of the requesting tenant.")
    private String requestSource;
    @NotBlank(message = "Incomplete Request: Provide the client token")
    private String clientToken;
    private Deposit data;
}
