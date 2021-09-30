package com.example.webmagazin.payloat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ApiResponseModel extends ApiResponse{
private Object object;

    public ApiResponseModel(Object object) {
        this.object = object;
    }

    public ApiResponseModel(String message, int code, Object object) {
        super(message, code);
        this.object = object;
    }
}
