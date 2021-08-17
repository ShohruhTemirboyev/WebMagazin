package com.example.webmagazin.payloat;

import com.example.webmagazin.entity.Address;
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
    private String number;
    private ResAddres resAddres;
    private Integer location;

}
