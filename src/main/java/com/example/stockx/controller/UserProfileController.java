package com.example.stockx.controller;

import com.example.stockx.dtos.request.UpdatePasswordRequest;
import com.example.stockx.dtos.request.UpdateUserRequest;
import com.example.stockx.dtos.response.ClientDetailsResponse;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.InvestmentProfileResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.user_profile.UserProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileUseCase userProfileUseCase;
    @GetMapping("/profile")
    public ResponseEntity<GlobalResponse<ClientDetailsResponse>> getClient() throws InvalidRequestException {
        GlobalResponse<ClientDetailsResponse> response = new GlobalResponse<>(userProfileUseCase.getClient());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("/profile/update-user-info")
    public ResponseEntity<GlobalResponse<String>> updateUser(@RequestBody UpdateUserRequest request) throws InvalidRequestException {
        GlobalResponse<String> response = new GlobalResponse<>(userProfileUseCase.updateUserDetails(request));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/profile/update-password")
    public ResponseEntity<GlobalResponse<String>> updateUser(@RequestBody UpdatePasswordRequest request) throws InvalidRequestException {
        GlobalResponse<String> response = new GlobalResponse<>(userProfileUseCase.updatePassword(request));
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/investment-profile")
    public ResponseEntity<GlobalResponse<InvestmentProfileResponse>> getInvestmentProfile() throws InvalidRequestException {
        GlobalResponse<InvestmentProfileResponse> response = new GlobalResponse<>(userProfileUseCase.getInvestmentProfileData());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
