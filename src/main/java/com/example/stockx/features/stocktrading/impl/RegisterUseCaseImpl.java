package com.example.stockx.features.stocktrading.impl;

import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.features.stocktrading.RegisterUseCase;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseCaseImpl implements RegisterUseCase {
    @Override
    public SignupResponse registerCustomer(SignupRequest request) {
        return null;
    }

    @Override
    public LoginResponse loginCustomer(LoginRequest request) {
        return null;
    }

    @Override
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request) {
        return null;
    }

    @Override
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request) {
        return null;
    }

    @Override
    public ClientDetailsResponse getClientDetails() {
        return null;
    }

    @Override
    public InvestmentProfileResponse getInvestmentProfileData() {
        return null;
    }
}
