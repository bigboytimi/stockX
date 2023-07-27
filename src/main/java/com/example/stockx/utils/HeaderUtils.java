package com.example.stockx.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeaderUtils {
    public static HttpHeaders setHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        return headers;
    }

    public static HttpHeaders setHeaders(String clientToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        headers.set("x-client-token", clientToken);
        return headers;
    }

    public static HttpHeaders setHeaders(String clientToken, String requestSource){
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept-language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x_subject_type", "standard");
        headers.set("x-client-token", clientToken);
        headers.set("x_request_source", requestSource);
        return headers;
    }
}
