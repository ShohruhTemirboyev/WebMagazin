package com.example.webmagazin.controller;

import com.example.webmagazin.entity.Product;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ReqProduct;
import com.example.webmagazin.repository.ProductRepository;
import com.example.webmagazin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @PostMapping("/addProduct")
    public  HttpEntity<?> addProduct(@RequestBody ReqProduct reqProduct){
        ApiResponse response=productService.saveProduct(reqProduct);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("{id}")
    public HttpEntity<?> getAttachment(@PathVariable UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("GetUser"));
    return ResponseEntity.ok().body(product);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteProduct(@PathVariable UUID id){
        ApiResponse response=productService.deleteProduct(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(response);
    }
    @GetMapping("/getProduct")
    public HttpEntity<?> getUsers(){
        return ResponseEntity.ok(productRepository.findAll());
    }


}
