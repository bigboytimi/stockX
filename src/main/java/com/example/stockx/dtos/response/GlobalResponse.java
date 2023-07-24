package com.example.stockx.dtos.response;

import com.example.stockx.utils.DateUtils;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class GlobalResponse<T> {
    private String message;
    private String timestamp;
    private T data;

    public GlobalResponse(T data) {
        this.message = "Request Successfully Processed";
        this.timestamp = DateUtils.saveLocalDate(LocalDateTime.now());
        this.data = data;
    }
}
