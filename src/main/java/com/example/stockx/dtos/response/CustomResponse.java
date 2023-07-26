package com.example.stockx.dtos.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
    private T data;

}
