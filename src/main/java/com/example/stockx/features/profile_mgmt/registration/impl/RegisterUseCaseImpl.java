package com.example.stockx.features.profile_mgmt.registration.impl;

import com.example.stockx.dtos.payload.Affiliation;
import com.example.stockx.dtos.payload.Identity;
import com.example.stockx.dtos.payload.PhoneVerification;
import com.example.stockx.dtos.payload.Registration;
import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.enums.Gender;
import com.example.stockx.exception.*;
import com.example.stockx.features.profile_mgmt.registration.RegisterUseCase;
import com.example.stockx.model.Customer;
import com.example.stockx.model.InvestmentProfile;
import com.example.stockx.repository.CustomerRepository;
import com.example.stockx.service.StockTradingService;
import com.example.stockx.utils.PasswordUtils;
import com.example.stockx.utils.ResponseParserUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.RequestFailedException;
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
    private final ResponseParserUtils parserUtils;
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


        String apiResponseBody = apiResponse.getBody();

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(apiResponseBody, responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new LoginFailedException(message);
        }
    }

    @Override
    public String createInvestmentProfile(InvestmentProfileRequest request) throws InvalidRequestException {
        String clientToken = request.getClientToken();
        InvestmentProfile profileData = request.getData();

        String requestBody = gson.toJson(profileData);


        ResponseEntity<String> apiResponse = stockTradingService.createProfile(clientToken, requestBody);


        String responseBody = apiResponse.getBody();

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Investment Profile successfully created";
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(responseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public TokenRefreshResponse refreshCustomerToken(TokenRefreshRequest request) {
        String requestBody = gson.toJson(request);

        Type responseType = new TypeToken<TokenRefreshResponse>() {}.getType();
        ResponseEntity<String> apiResponse = stockTradingService.refreshToken(requestBody);

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(apiResponse.getBody(), responseType);
        } else {
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new AuthenticationFailedException(message);
        }
    }

    @Override
    public PhoneVerificationResponse verifyPhoneNumber(PhoneVerificationRequest request) {
       String clientToken = request.getClientToken();
       PhoneVerification phoneDetails = request.getData();

        String requestBody = gson.toJson(phoneDetails);

        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.verifyNumber(clientToken, requestBody);
        String apiResponseBody = apiResponse.getBody();

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return gson.fromJson(apiResponseBody, responseType);
        } else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponseBody, new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }

    }

    @Override
    public ClientDetailsResponse getClient() throws InvalidRequestException {
        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        ResponseEntity<String> apiResponse = stockTradingService.getClientDetails();

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

        ResponseEntity<String> apiResponse = stockTradingService.getInvestmentDetails();

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
        
        ResponseEntity<String> apiResponse = stockTradingService.updateUserInfo(requestBody);

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

        ResponseEntity<String> apiResponse = stockTradingService.updatePassword(requestBody);

        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Password successfully updated";
        }else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new InvalidRequestException(message);
        }
    }

    @Override
    public String verifyUserIdentity(IdentityRequest request) {
        String clientToken = request.getClientToken();
        Identity identityDetails = request.getData();

        String requestBody = gson.toJson(identityDetails);

        ResponseEntity<String> apiResponse = stockTradingService.verifyIdentity(clientToken, requestBody);



        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Identity queued for verification";
        } else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }

    }

    @Override
    public String addAffiliation(AffiliationRequest request) {
        String clientToken = request.getClientToken();
        Affiliation affiliationData = request.getData();

        String requestBody = gson.toJson(affiliationData);


        ResponseEntity<String> apiResponse = stockTradingService.createAffiliation(clientToken, requestBody);
        if (apiResponse.getStatusCode().is2xxSuccessful()){
            return "Investment profile created";
        } else{
            CustomResponse<Map<String, Object>> customResponse = parserUtils.parseApiResponse(apiResponse.getBody(), new TypeToken<CustomResponse<Map<String, Object>>>() {});
            Map<String, Object> data = customResponse.getData();
            String message = (String) data.get("message");
            throw new VerificationFailedException(message);
        }
    }



}
