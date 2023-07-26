package com.example.stockx.features.profile_mgmt.registration;


import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.exception.InvalidRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface RegisterUseCase {
    public SignupResponse registerCustomer(SignupRequest request);
    public LoginResponse loginCustomer(LoginRequest request) throws JsonProcessingException;
    public InvestmentProfileResponse createInvestmentProfile(InvestmentProfileRequest request);
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request);
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request);
    public ClientDetailsResponse getClient() throws InvalidRequestException;
    public InvestmentProfileResponse getInvestmentProfileData();
    public String updateUserDetails(UpdateUserRequest request) throws InvalidRequestException;
    public String updatePassword(UpdatePasswordRequest request) throws InvalidRequestException;
    public String verifyUserIdentity(IdentityRequest request);
    public String addAffiliation(AffiliationRequest request);
}
