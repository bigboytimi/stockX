package com.example.stockx.features.profile_mgmt.registration.impl;

import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.features.profile_mgmt.registration.RegisterUseCase;
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
    public InvestmentProfileResponse createInvestmentProfile(InvestmentProfileRequest request) {
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
    public ClientDetailsResponse getClient() {
        return null;
    }

    @Override
    public InvestmentProfileResponse getInvestmentProfileData() {
        return null;
    }

    @Override
    public UpdateUserResponse updateUserDetails(UpdateUserRequest request) {
        return null;
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest request) {
        return null;
    }

    @Override
    public String verifyUserIdentity(IdentityRequest request) {
        return null;
    }

    @Override
    public String addAffiliation(AffiliationRequest request) {
        return null;
    }
}
