package com.example.stockx.model;

import com.example.stockx.enums.CountryCode;
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
    private CountryCode residence_country_code;
    private String referral_code;
    private String phone_number;
    private String password;
    private String name;
    private String gender;
    private String email;
    private String date_of_birth;
    private String country_code;
    private String citizenship;
    @Embedded
    private InvestmentProfile investmentProfile;
}
