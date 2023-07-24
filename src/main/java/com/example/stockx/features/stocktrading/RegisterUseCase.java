package com.example.stockx.features.stocktrading;


import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;

public interface RegisterUseCase {
    public SignupResponse registerCustomer(SignupRequest request);
    public LoginResponse loginCustomer(LoginRequest request);
    public InvestmentProfileResponse createInvestmentProfile(InvestmentProfileRequest request);
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request);
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request);
    public ClientDetailsResponse getClientDetails();
    public InvestmentProfileResponse getInvestmentProfileData();

    public UpdateUserResponse updateUserDetails(UpdateUserRequest request);
}
