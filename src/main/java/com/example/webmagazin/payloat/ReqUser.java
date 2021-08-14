package com.example.webmagazin.payloat;

import lombok.Data;



@Data
public class ReqUser {
    private String phoneNumber;
    private String password;
    private String userName;
    private String birthDate;
}
