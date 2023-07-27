package com.example.stockx.features.registration;


import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegisterUseCase {
    public SignupResponse registerCustomer(SignupRequest request);
    public LoginResponse loginCustomer(LoginRequest request) throws JsonProcessingException;
    public String createInvestmentProfile(InvestmentProfileRequest request) throws InvalidRequestException;
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request);
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request);
    public String verifyUserIdentity(IdentityRequest request);
    public String addAffiliation(AffiliationRequest request);
}
