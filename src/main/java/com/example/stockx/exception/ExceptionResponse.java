package com.example.stockx.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private Integer statusCode;
    private String timeStamp;
}
