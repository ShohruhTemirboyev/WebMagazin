package com.example.webmagazin.controller;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.payloat.ApiResponseModel;
import com.example.webmagazin.payloat.ReqComentarya;
import com.example.webmagazin.security.CurrentUser;
import com.example.webmagazin.service.ComentaryaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product/comentarya")
public class ComentaryaController {
    @Autowired
    ComentaryaService comentaryaService;

    @PostMapping()
public HttpEntity<?> addComent(@CurrentUser User user, @RequestBody ReqComentarya reqComentarya){
        ApiResponse response=comentaryaService.addComentarya(user,reqComentarya.getProductId(), reqComentarya.getText());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{comentId}")
    public HttpEntity<?> deleteComent(@PathVariable Long comentId){
        ApiResponse response=comentaryaService.deleteComent(comentId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getComent/{comentId}")
    public HttpEntity<?> getComent(@PathVariable Long comentId){
        ApiResponseModel responseModel=comentaryaService.getComent(comentId);
        return ResponseEntity.ok(responseModel);
    }
    @GetMapping("/getComents/{productId}")
    public HttpEntity<?> getComents(@PathVariable UUID productId){
        ApiResponseModel responseModel=comentaryaService.getAllProductComent(productId);
        return ResponseEntity.ok(responseModel);

    }
}
