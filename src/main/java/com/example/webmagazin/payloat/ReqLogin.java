package com.example.webmagazin.payloat;

import lombok.Data;

@Data
public class ReqLogin {
    private String phoneNumber;
    private String password;
}
