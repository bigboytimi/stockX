package com.example.stockx.model;

import com.example.stockx.enums.CountryCode;
import com.example.stockx.enums.Gender;
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
public class Client {
    @Id
    private int id;
    private String surname;
    @SerializedName("residence_country_code")
    private CountryCode residenceCountryCode;
    @SerializedName("referral_code")
    private String referralCode;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @SerializedName("country_code")
    private String countryCode;
    private String citizenship;
    @Embedded
    private InvestmentProfile investmentProfile;
}
