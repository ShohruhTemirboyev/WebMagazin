package com.example.webmagazin.payloat;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ResProduct {
    private String description;
    private Double price;
    private String productTypeName;
    private List<UUID> attachmentId;
    private Integer count;
    private Boolean bestseller;
    private Boolean superPrice;
    private Boolean newProduct;
    private String vendor;

}
