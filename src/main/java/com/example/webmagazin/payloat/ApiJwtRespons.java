package com.example.webmagazin.payloat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiJwtRespons  {

    private String message;
    private int code;
    private UUID userId;
    private String token;
    private String tokenType = "Token";

    public ApiJwtRespons(String token) {
        this.token = token;
    }


}
