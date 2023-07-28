package com.example.stockx.controller;

import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.registration.RegisterUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegisterUseCase registerUseCase;

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<SignupResponse>> registerUser(@RequestBody SignupRequest request){
        GlobalResponse<SignupResponse> response = new GlobalResponse<>(registerUseCase.registerCustomer(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<LoginResponse>> loginUser(@RequestBody LoginRequest request) throws JsonProcessingException {
        GlobalResponse<LoginResponse> response = new GlobalResponse<>(registerUseCase.loginCustomer(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<GlobalResponse<TokenRefreshResponse>> refreshToken(@RequestBody TokenRefreshRequest request) throws JsonProcessingException {
        GlobalResponse<TokenRefreshResponse> response = new GlobalResponse<>(registerUseCase.refreshCustomerToken(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-number")
    public ResponseEntity<GlobalResponse<PhoneVerificationResponse>> verifyPhoneNumber(@RequestBody PhoneVerificationRequest request){
        GlobalResponse<PhoneVerificationResponse> response = new GlobalResponse<>(registerUseCase.verifyPhoneNumber(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-investment-profile")
    public ResponseEntity<GlobalResponse<String>> createInvestmentProfile(@RequestBody InvestmentProfileRequest request) throws InvalidRequestException {
        GlobalResponse<String> response = new GlobalResponse<>(registerUseCase.createInvestmentProfile(request));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/verify-identity")
    public ResponseEntity<GlobalResponse<String>> verifyIdentity(@RequestBody IdentityRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(registerUseCase.verifyUserIdentity(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-affiliations")
    public ResponseEntity<GlobalResponse<String>> addAffiliations(@RequestBody AffiliationRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(registerUseCase.addAffiliation(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
