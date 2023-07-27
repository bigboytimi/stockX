package com.example.stockx.dtos.payload;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Deposit {
    @SerializedName("payment_method_id")
    private int paymentMethodId;
    @SerializedName("deposit_type")
    private String depositType;
    @SerializedName("destination_wallet_id")
    private Number destinationWalletId;
    private Number amount;
}
