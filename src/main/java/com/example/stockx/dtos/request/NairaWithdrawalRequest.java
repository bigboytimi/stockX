package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.NairaWithdrawal;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NairaWithdrawalRequest {
    @NotBlank(message = "Please add client token")
    private HeaderRequest headerRequest;
    @NotBlank(message = "Include request for withdrawal")
    private NairaWithdrawal request;
}
