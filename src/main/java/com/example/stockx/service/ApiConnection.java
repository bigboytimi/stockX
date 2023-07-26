package com.example.stockx.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ApiConnection {
    public <T, R> R connectAndPost(HttpHeaders headers, T postBody, String url, HttpMethod method, Class<R> responseBody);
    public ResponseEntity<String> connectAndGet(HttpHeaders headers, String url, HttpMethod method);

    public ResponseEntity<String> postAndGetResponseEntity(HttpHeaders headers, String postBody, String url, HttpMethod method);
}
