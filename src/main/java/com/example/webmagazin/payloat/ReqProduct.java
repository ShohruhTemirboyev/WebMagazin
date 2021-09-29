package com.example.webmagazin.payloat;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqProduct {
    private String description;
    private Double newPrice;
    private Double oldPrice;
    private Long productType;
    private String productTypeName;
    private List<UUID> attachmentId;
    private Integer count;

    private String vendor;

}
