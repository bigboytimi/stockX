package com.example.stockx.service.impl;

import com.example.stockx.exception.APIConnectionException;
import com.example.stockx.service.ApiConnection;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class ApiConnectionImpl implements ApiConnection {

    @Value("${app-key}")
    private String appKey;
    @Override
    public <T, R> R connectAndPost(HttpHeaders headers, T postBody, String url, HttpMethod method, Class<R> responseClass) {

        HttpEntity<T> httpEntity = new HttpEntity<>(postBody, headers);

        try{
            ResponseEntity<R> response = getRestTemplate().exchange(
                    url, method,httpEntity,responseClass);
            return response.getBody();
        }catch(Exception e){
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }

    }

    @Override
    public <R> R connectAndGet(String url, HttpMethod method, Class<R> responseClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept_language", "en-US");
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("app-key", "appKey");

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try{
            ResponseEntity<R> response = getRestTemplate().exchange(
                    url, method,httpEntity,responseClass);
            return response.getBody();
        }catch(Exception e){
            e.printStackTrace();
            throw new APIConnectionException("Request Failed, Please Try Again");
        }
    }


    private static RestTemplate getRestTemplate() {

        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectionRequestTimeout(45, TimeUnit.SECONDS)
                        .setResponseTimeout(40,TimeUnit.SECONDS)
                        .build())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }
}
