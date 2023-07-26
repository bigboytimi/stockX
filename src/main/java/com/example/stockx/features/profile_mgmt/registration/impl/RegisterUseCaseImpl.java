package com.example.stockx.features.profile_mgmt.registration.impl;

import com.example.stockx.dtos.payload.Affiliation;
import com.example.stockx.dtos.payload.Identity;
import com.example.stockx.dtos.payload.PhoneVerification;
import com.example.stockx.dtos.payload.Registration;
import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.enums.Gender;
import com.example.stockx.exception.APIConnectionException;
import com.example.stockx.exception.LoginFailedException;
import com.example.stockx.exception.VerificationFailedException;
import com.example.stockx.features.profile_mgmt.registration.RegisterUseCase;
import com.example.stockx.model.Customer;
import com.example.stockx.model.InvestmentProfile;
import com.example.stockx.repository.CustomerRepository;
import com.example.stockx.service.StockTradingService;
import com.example.stockx.utils.PasswordUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegisterUseCaseImpl implements RegisterUseCase {
    private final StockTradingService stockTradingService;
    private final Gson gson;
    private final PasswordUtils encoder;
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;
    @Override
    public SignupResponse registerCustomer(SignupRequest request) {
        String clientToken = request.getClientToken();
        Registration registrationData = request.getData();

        String requestBody = gson.toJson(registrationData);

        Type responseType = new TypeToken<SignupResponse>(){}.getType();
        /*
        Send request to Bamboo
         */
        String apiResponse = stockTradingService.register(clientToken, requestBody);
        /*
        Convert apiResponse to a SignupResponse Object
         */
        SignupResponse response = gson.fromJson(apiResponse, responseType);
        /*
        Build and save customer details
         */
        Customer customer = Customer.builder()
                .surname(response.getLast_name())
                .name(response.getFirst_name())
                .fullName(response.getFull_name())
                .email(response.getEmail())
                .dateOfBirth(response.getDate_of_birth())
                .phoneNumber(response.getPhone_number())
                .citizenship(registrationData.getCitizenship())
                .gender(Enum.valueOf(Gender.class, registrationData.getGender()))
                .country(response.getResidence_country())
                .referralCode(registrationData.getReferral_code())
                .residenceCountryCode(registrationData.getCountry_code())
                .password(encoder.encode(registrationData.getPassword()))
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        return response;
    }

    @Override
    public LoginResponse loginCustomer(LoginRequest request) throws JsonProcessingException {
       String requestBody = gson.toJson(request);
       Type responseType = new TypeToken<LoginResponse>(){}.getType();

       ResponseEntity<String> apiResponse = stockTradingService.loginUser(requestBody);

        HttpStatusCode statusCode = apiResponse.getStatusCode();
        String apiResponseBody = apiResponse.getBody();

        if (statusCode.is2xxSuccessful()){
            return gson.fromJson(apiResponseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parseApiResponse(apiResponseBody, new TypeReference<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new LoginFailedException(message);
        }
    }

    @Override
    public InvestmentProfileResponse createInvestmentProfile(InvestmentProfileRequest request) {
        String clientToken = request.getClientToken();
        InvestmentProfile profileData = request.getData();

        String requestBody = gson.toJson(profileData);
        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.createProfile(clientToken, requestBody);
        HttpStatusCode statusCode = extractStatusCode(apiResponse);

        String responseBody = apiResponse.getBody();

        if (statusCode.is2xxSuccessful()){
            return gson.fromJson(responseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parseApiResponse(responseBody, new TypeReference<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new LoginFailedException(message);
        }
    }

    @Override
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request) {
        return null;
    }

    @Override
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request) {
       String clientToken = request.getClientToken();
       PhoneVerification phoneDetails = request.getData();

        String requestBody = gson.toJson(phoneDetails);

        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.verifyNumber(clientToken, requestBody);
        HttpStatusCode statusCode = extractStatusCode(apiResponse);
        String apiResponseBody = apiResponse.getBody();

        if (statusCode.is2xxSuccessful()){
            return gson.fromJson(apiResponseBody, responseType);
        } else{
            CustomResponse<Map<String, Object>> customResponse = parseApiResponse(apiResponseBody, new TypeReference<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }

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
        String clientToken = request.getClientToken();
        Identity identityDetails = request.getData();

        String requestBody = gson.toJson(identityDetails);
        Type responseType = new TypeToken<String>(){}.getType();
        String apiResponse = stockTradingService.verifyIdentity(clientToken, requestBody);

    }

    @Override
    public String addAffiliation(AffiliationRequest request) {
        String clientToken = request.getClientToken();
        Affiliation affiliationData = request.getData();

        String requestBody = gson.toJson(affiliationData);

        Type responseType = new TypeToken<String>(){}.getType();
        String apiResponse = stockTradingService.createAffiliation(clientToken, requestBody);

    }

    public static <T> CustomResponse<T> parseApiResponse(String jsonResponse, TypeReference<CustomResponse<Map<String, Object>>> valueType){
        CustomResponse<T> customResponse;
        try {
            customResponse = objectMapper.readValue(jsonResponse, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }
        return customResponse;
    }

    public static HttpStatusCode extractStatusCode(ResponseEntity<String> entity){
        return entity.getStatusCode();
    }
}
