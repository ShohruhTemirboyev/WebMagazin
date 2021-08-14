package com.example.webmagazin.payloat;

import lombok.Data;



@Data
public class ReqUser {

    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate;
    private String email;
    private String nomer;
    private String addres;
    private Integer location;

}
