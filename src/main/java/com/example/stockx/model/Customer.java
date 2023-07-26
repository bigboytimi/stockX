package com.example.stockx.model;

import com.example.stockx.enums.Gender;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String surname;
    @SerializedName("residence_country_code")
    private String residenceCountryCode;
    @SerializedName("referral_code")
    private String referralCode;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String password;
    private String name;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    @SerializedName("date_of_birth")
    private String dateOfBirth;
    @SerializedName("country_code")
    private String country;
    private String citizenship;
    @Embedded
    private InvestmentProfile investmentProfile;
}
