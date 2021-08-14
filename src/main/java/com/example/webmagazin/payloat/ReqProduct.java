package com.example.webmagazin.payloat;

import lombok.Data;

import java.util.List;

@Data
public class ReqProduct {
    private Long productTypeId;
    private List<Long> productSalesTypeId;
    private String productName;
    private Long price;
    private Long currencyId;
    private String status;
    private String dicription;
    private String attachmentId;

}
