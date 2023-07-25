package com.example.stockx.controller;

import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.features.profile_mgmt.registration.RegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GlobalResponse<String>> verifyIdentity(@RequestBody IdentityRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(registerUseCase.verifyUserIdentity(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add-affiliations")
    public ResponseEntity<GlobalResponse<String>> addAffiliations(@RequestBody AffiliationRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(registerUseCase.addAffiliation(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<GlobalResponse<ClientDetailsResponse>> getClient(){
        GlobalResponse<ClientDetailsResponse> response = new GlobalResponse<>(registerUseCase.getClient());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("/profile/update-user-info")
    public ResponseEntity<GlobalResponse<UpdateUserResponse>> updateUser(@RequestBody UpdateUserRequest request){
        GlobalResponse<UpdateUserResponse> response = new GlobalResponse<>(registerUseCase.updateUserDetails(request));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/profile/update-password")
    public ResponseEntity<GlobalResponse<UpdatePasswordResponse>> updateUser(@RequestBody UpdatePasswordRequest request){
        GlobalResponse<UpdatePasswordResponse> response = new GlobalResponse<>(registerUseCase.updatePassword(request));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
