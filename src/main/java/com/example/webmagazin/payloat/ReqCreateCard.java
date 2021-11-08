package com.example.webmagazin.payloat;

import lombok.Data;

@Data
public class ReqCreateCard {
private String number;
private String expire;
private Double amount;
}
