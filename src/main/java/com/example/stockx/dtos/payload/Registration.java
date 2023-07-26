package com.example.stockx.dtos.payload;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Registration {
    private static final String COUNTRY_CODE_PATTERN = "^[A-Z]{3}$";
    @NotNull(message = "Client token cannot be null")
    private String clientToken;
    private String surname;
    @Pattern(regexp = COUNTRY_CODE_PATTERN, message = "Invalid country code. It should be 3 uppercase letters.")
    private String residence_country_code;
    private String referral_code;
    private String phone_number;
    private String password;
    private String name;
    @Pattern(regexp = "^(?i)(?:man|woman)$", message = "Invalid Gender choice. Should either be Man or Woman")
    private String gender;
    private String email;
    @NotNull(message = "Date field must not be null")
    @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", message = "Invalid date format. Expected format: YYYY-MM-DD")
    private String date_of_birth;
    private String country_code;
    private String citizenship;
}
