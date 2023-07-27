package com.example.stockx.controller;


import com.example.stockx.dtos.request.DepositRequest;
import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.AccountDetailsResponse;
import com.example.stockx.dtos.response.DepositLinkResponse;
import com.example.stockx.dtos.response.DepositsResponse;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.exception.InvalidRequestException;
import com.example.stockx.features.account_mgmt.AccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountUseCase accountUseCase;
    @PostMapping("/deposit-money")
    public ResponseEntity<GlobalResponse<DepositLinkResponse>> depositMoney(@RequestBody DepositRequest depositRequest) throws InvalidRequestException {
        GlobalResponse<DepositLinkResponse> response = new GlobalResponse<>(accountUseCase.depositMoney(depositRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/view-account-details/{customer_id}")
    public ResponseEntity<GlobalResponse<AccountDetailsResponse>> getAccountDetails(@PathVariable("customer_id") Integer userId, HeaderRequest request) throws InvalidRequestException {
        GlobalResponse<AccountDetailsResponse> response = new GlobalResponse<>(accountUseCase.getAccountDetails(userId, request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/deposits")
    public ResponseEntity<GlobalResponse<DepositsResponse>> getDeposits(HeaderRequest request,
                                                                        @RequestParam(name = "limit") String limit,
                                                                        @RequestParam(name = "next_token") Integer nextToken,
                                                                        @RequestParam(name = "start_date") String startDate,
                                                                        @RequestParam(name = "end_date") String endDate){
        GlobalResponse<DepositsResponse> response = new GlobalResponse<>(accountUseCase.getDeposits(request, limit, nextToken, startDate, endDate));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
