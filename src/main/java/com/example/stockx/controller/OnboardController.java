package com.example.stockx.controller;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.GlobalResponse;
import com.example.stockx.dtos.response.OnboardResponse;
import com.example.stockx.dtos.response.PortfolioBreakdownResponse;
import com.example.stockx.features.onboard.OnboardUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/onboard")
@RequiredArgsConstructor
public class OnboardController {
    private final OnboardUseCase onboardUseCase;

    @GetMapping("/actions")
    public ResponseEntity<GlobalResponse<OnboardResponse>> getOnboardActions(@RequestBody HeaderRequest request){
        GlobalResponse<OnboardResponse> response = new GlobalResponse<>(onboardUseCase.getOnboardActions(request));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
