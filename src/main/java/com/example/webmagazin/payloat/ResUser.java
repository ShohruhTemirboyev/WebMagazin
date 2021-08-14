package com.example.webmagazin.payloat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResUser {
    private String phoneNumber;
    private String password;
    private String userName;
    private String birthDate;

    public ResUser(String phoneNumber, String password, String userName, String birthDate) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
        this.birthDate = birthDate;
    }
}
