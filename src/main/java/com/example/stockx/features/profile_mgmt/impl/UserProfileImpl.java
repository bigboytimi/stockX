package com.example.stockx.features.profile_mgmt.impl;

import com.example.stockx.dtos.request.UpdatePasswordRequest;
import com.example.stockx.dtos.request.UpdateUserRequest;
import com.example.stockx.dtos.response.ClientDetailsResponse;
import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.dtos.response.InvestmentProfileResponse;
import com.example.stockx.dtos.response.PhoneVerificationResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.profile_mgmt.UserProfileUseCase;
import com.example.stockx.service.UserProfileService;
import com.example.stockx.utils.GsonSingleton;
import com.example.stockx.utils.ResponseParserUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserProfileImpl implements UserProfileUseCase {
    Gson gson = GsonSingleton.getInstance();
    private final UserProfileService userProfileService;
    private final ResponseParserUtils parserUtils;
    @Override
    public ClientDetailsResponse getClient() throws InvalidRequestException {
        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        ResponseEntity<String> apiResponse = userProfileService.getClientDetails();

        String responseBody = apiResponse.getBody();

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public InvestmentProfileResponse getInvestmentProfileData() throws InvalidRequestException {
        Type responseType = new TypeToken<InvestmentProfileResponse>(){}.getType();

        ResponseEntity<String> apiResponse = userProfileService.getInvestmentDetails();

        String responseBody = apiResponse.getBody();
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public String updateUserDetails(UpdateUserRequest request) throws InvalidRequestException {
        String requestBody = gson.toJson(request);

        ResponseEntity<String> apiResponse = userProfileService.updateUserInfo(requestBody);

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Profile successfully updated";
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public String updatePassword(UpdatePasswordRequest request) throws InvalidRequestException {
        String requestBody = gson.toJson(request);

        ResponseEntity<String> apiResponse = userProfileService.updatePassword(requestBody);

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Password successfully updated";
        }else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

}
