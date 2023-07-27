package com.example.stockx.features.onboard;

import com.example.stockx.dtos.request.HeaderRequest;
import com.example.stockx.dtos.response.OnboardResponse;

public interface OnboardUseCase {
    public OnboardResponse getOnboardActions(HeaderRequest request);

}
