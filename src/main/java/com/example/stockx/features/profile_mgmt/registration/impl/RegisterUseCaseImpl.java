package com.example.stockx.features.profile_mgmt.registration.impl;

import com.example.stockx.dtos.payload.Affiliation;
import com.example.stockx.dtos.payload.Identity;
import com.example.stockx.dtos.payload.PhoneVerification;
import com.example.stockx.dtos.payload.Registration;
import com.example.stockx.dtos.request.*;
import com.example.stockx.dtos.response.*;
import com.example.stockx.enums.Gender;
import com.example.stockx.features.profile_mgmt.registration.RegisterUseCase;
import com.example.stockx.model.Customer;
import com.example.stockx.model.InvestmentProfile;
import com.example.stockx.repository.CustomerRepository;
import com.example.stockx.service.StockTradingService;
import com.example.stockx.utils.PasswordUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
@RequiredArgsConstructor
public class RegisterUseCaseImpl implements RegisterUseCase {
    private final StockTradingService stockTradingService;
    private final Gson gson;
    private final PasswordUtils encoder;
    private final CustomerRepository customerRepository;
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
    public LoginResponse loginCustomer(LoginRequest request) {
       String requestBody = gson.toJson(request);
       Type responseType = new TypeToken<LoginResponse>(){}.getType();

       LoginResponse apiResponse = stockTradingService.loginUser(requestBody);
       return apiResponse;
    }

    @Override
    public InvestmentProfileResponse createInvestmentProfile(InvestmentProfileRequest request) {
        String clientToken = request.getClientToken();
        InvestmentProfile profileData = request.getData();

        String requestBody = gson.toJson(profileData);
        Type responseType = new TypeToken<PhoneVerificationResponse>(){}.getType();

        String apiResponse = stockTradingService.createProfile(clientToken, requestBody);

        return gson.fromJson(apiResponse, responseType);
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

        String apiResponse = stockTradingService.verifyNumber(clientToken, requestBody);

        //todo: extract only the refresh token and jwt from the response
        return gson.fromJson(apiResponse, responseType);
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
        CustomResponse apiResponse = stockTradingService.verifyIdentity(clientToken, requestBody);

        return apiResponse.getMessage();
    }

    @Override
    public String addAffiliation(AffiliationRequest request) {
        String clientToken = request.getClientToken();
        Affiliation affiliationData = request.getData();

        String requestBody = gson.toJson(affiliationData);

        Type responseType = new TypeToken<String>(){}.getType();
        CustomResponse apiResponse = stockTradingService.createAffiliation(clientToken, requestBody);
        return apiResponse.getMessage();
    }

}
