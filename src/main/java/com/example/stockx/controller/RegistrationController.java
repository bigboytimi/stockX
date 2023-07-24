package com.example.stockx.controller;

import com.example.stockx.dtos.request.InvestmentProfileRequest;
import com.example.stockx.dtos.request.PhoneVerificationRequest;
import com.example.stockx.dtos.request.SignupRequest;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.InvestmentProfileResponse;
import com.example.stockx.dtos.response.PhoneVerificationResponse;
import com.example.stockx.dtos.response.SignupResponse;
import com.example.stockx.features.stocktrading.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterUseCase registerUseCase;

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<SignupResponse>> registerUser(@RequestBody SignupRequest request){
        GlobalResponse<SignupResponse> response = new GlobalResponse<>(registerUseCase.registerCustomer(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verify-number")
    public ResponseEntity<GlobalResponse<PhoneVerificationResponse>> verifyPhoneNumber(@RequestBody PhoneVerificationRequest request){
        GlobalResponse<PhoneVerificationResponse> response = new GlobalResponse<>(registerUseCase.verifyPhoneNumber(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-investment-profile")
    public ResponseEntity<GlobalResponse<InvestmentProfileResponse>> createInvestmentProfile(@RequestBody InvestmentProfileRequest request){
        GlobalResponse<InvestmentProfileResponse> response = new GlobalResponse<>(registerUseCase.createInvestmentProfile(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verify-identity")
    public ResponseEntity<GlobalResponse<>>
}
