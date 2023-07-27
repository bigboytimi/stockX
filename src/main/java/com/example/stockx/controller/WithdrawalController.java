package com.example.stockx.controller;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.request.NairaWithdrawalRequest;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.WithdrawStatusResponse;
import com.example.stockx.features.withdrawal.WithdrawalUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/withdraw")
@RequiredArgsConstructor
public class WithdrawalController {
    private WithdrawalUseCase withdrawalUseCase;

    @PostMapping("/naira-withdrawal")
    public ResponseEntity<GlobalResponse<String>> withdrawInNaira(@RequestBody NairaWithdrawalRequest request){
        GlobalResponse<String> response = new GlobalResponse<>(withdrawalUseCase.withdrawNaira(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/withdrawal-status/{withdrawal_reference}")
    public ResponseEntity<GlobalResponse<WithdrawStatusResponse>> getWithdrawalStatus(@PathVariable("withdrawal_reference") String reference, @RequestBody HeaderRequest request){
        GlobalResponse<WithdrawStatusResponse> response = new GlobalResponse<>(withdrawalUseCase.getWithdrawalStatus(reference, request));
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
