package com.example.stockx.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {
    private String residence_country;
    private String phone_number;
    private String last_name;
    private String full_name;
    private String first_name;
    private String email;
    private String date_of_birth;
}
