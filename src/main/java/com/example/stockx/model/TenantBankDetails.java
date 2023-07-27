package com.example.stockx.model;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tenant_bank_details")
public class TenantBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @SerializedName("swift_aba_code")
    private String swiftAbaCode;
    @SerializedName("intermediary_code")
    private String intermediaryCode;

    private String currency;
    @SerializedName("beneficiary_name")
    private String beneficiaryName;
    @SerializedName("bank_zip_code")
    private String bankZipCode;
    @SerializedName("bank_state")
    private String bankState;
    @SerializedName("bank_name")
    private String bankName;
    @SerializedName("bank_country")
    private String bankCountry;
    @SerializedName("bank_address")
    private String bankAddress;
    @SerializedName("additional_instructions")
    private String additionalInstructions;
    @SerializedName("account_number")
    private String accountNumber;
}
