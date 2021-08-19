package com.example.webmagazin.payloat;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqComentarya {

    private String text;
    private Integer personId;
    private UUID productId;

}
