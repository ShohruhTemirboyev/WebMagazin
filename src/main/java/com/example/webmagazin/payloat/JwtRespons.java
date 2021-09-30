package com.example.webmagazin.payloat;

import lombok.Data;

@Data
public class JwtRespons {
private String token;
private String tokenType="Bearer";

    public JwtRespons(String token) {
        this.token = token;
    }
}
