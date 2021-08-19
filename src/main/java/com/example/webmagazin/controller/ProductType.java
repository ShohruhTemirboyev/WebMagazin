package com.example.webmagazin.controller;

import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProductType;
import com.example.webmagazin.repository.ProductTypeRepository;
import com.example.webmagazin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productType")
public class ProductType {

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @PostMapping("/addType")
    public HttpEntity<?> saveType(@RequestBody ReqProductType reqProductType){
        ApiResponse response= productTypeService.saveType(reqProductType);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @GetMapping("/getType")
    public HttpEntity<?> getType(){
        return ResponseEntity.ok(productTypeRepository.findAll());
    }
}