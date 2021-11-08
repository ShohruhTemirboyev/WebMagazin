package com.example.webmagazin.payloat;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ResComent {
private String name;
private String text;
private UUID productId;
private Long id;
private Date date;
}
