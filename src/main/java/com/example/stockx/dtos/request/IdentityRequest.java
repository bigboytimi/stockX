package com.example.stockx.dtos.request;

import com.example.stockx.dtos.payload.Identity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class IdentityRequest {
    private String clientToken;
    private Identity request;
}
