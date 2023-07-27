package com.example.stockx.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderRequest {
    private String clientToken;
    private String requestSource;
}
