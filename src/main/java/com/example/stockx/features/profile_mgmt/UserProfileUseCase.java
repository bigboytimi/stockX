package com.example.stockx.features.profile_mgmt;

import com.example.stockx.dtos.request.UpdatePasswordRequest;
import com.example.stockx.dtos.request.UpdateUserRequest;
import com.example.stockx.dtos.response.ClientDetailsResponse;
import com.example.stockx.dtos.response.InvestmentProfileResponse;
import com.example.stockx.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

@Service
public interface UserProfileUseCase {
    public ClientDetailsResponse getClient() throws InvalidRequestException;
    public InvestmentProfileResponse getInvestmentProfileData() throws InvalidRequestException;
    public String updateUserDetails(UpdateUserRequest request) throws InvalidRequestException;
    public String updatePassword(UpdatePasswordRequest request) throws InvalidRequestException;
}
