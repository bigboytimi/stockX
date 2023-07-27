package com.example.stockx.controller;


import com.example.stockx.dtos.request.DepositRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.AccountDetailsResponse;
import com.example.stockx.dtos.response.DepositResponse;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.profile_mgmt.deposit.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountUseCase accountUseCase;
    @PostMapping("/deposit-money")
    public ResponseEntity<GlobalResponse<DepositResponse>> depositMoney(@RequestBody DepositRequest depositRequest) throws InvalidRequestException {
        GlobalResponse<DepositResponse> response = new GlobalResponse<>(accountUseCase.depositMoney(depositRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/view-account-details/{customer_id}")
    public ResponseEntity<GlobalResponse<AccountDetailsResponse>> getAccountDetails(@PathVariable("customer_id") Integer userId, HeaderRequest request){
        GlobalResponse<AccountDetailsResponse> response = new GlobalResponse<>(accountUseCase.getAccountDetails(userId, request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
