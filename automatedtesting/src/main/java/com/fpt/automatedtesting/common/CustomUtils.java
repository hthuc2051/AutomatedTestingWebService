package com.fpt.automatedtesting.common;

import org.springframework.http.*;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class CustomUtils {

    public static String getCurDateTime(Date date, String typeFormat) {
        String s = "";

        SimpleDateFormat formatter = null;
        switch (typeFormat) {
            case "Prefix":
                formatter = new SimpleDateFormat("dd_MM_yyyy_HH:mm:ss");
                s = formatter.format(date);
                break;
            case "DateOnly":
                formatter = new SimpleDateFormat("dd_MM_yyyy");
                s = formatter.format(date);
                break;
            default:
                formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                s = formatter.format(date);
                break;
        }
        return s;
    }
    public static String sendRequest(String url, String token) {
        String s = ":2pd5qm4n25nnn3j7k3gl7nn3znhde3mkpbfc54xoyg7kq2rwenua";
        byte[] bytes = s.getBytes(StandardCharsets.US_ASCII);
        String authString = "Basic " +
                Base64Utils.encodeToString(
                        bytes
                );
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // set custom header
        headers.set("Authorization", authString);

        // build the request
        HttpEntity request = new HttpEntity(headers);


        //Create a new HttpEntity
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        //Execute the method writing your HttpEntity to the request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class, 1);
        if (response != null) {
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        }
        return "";
    }
}
