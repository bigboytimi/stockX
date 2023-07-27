package com.example.stockx.utils;

import com.example.stockx.dtos.response.CustomResponse;
import com.example.stockx.exception.APIConnectionException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResponseParserUtils {
    private final Gson gson;
    public <T> CustomResponse<T> parseApiResponse(String jsonResponse, TypeToken<CustomResponse<Map<String, Object>>> valueType){
        CustomResponse<T> customResponse;
        try {
            customResponse = gson.fromJson(jsonResponse, valueType.getType());
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }
        return customResponse;
    }
}
