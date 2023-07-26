package com.example.stockx.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UpdateUserRequest {
    private String email;

}
