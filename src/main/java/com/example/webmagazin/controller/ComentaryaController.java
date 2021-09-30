package com.example.webmagazin.controller;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponse;
import com.example.webmagazin.security.CurrentUser;
import com.example.webmagazin.service.ComentaryaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product/comentarya")
public class ComentaryaController {
    @Autowired
    ComentaryaService comentaryaService;
    @PostMapping()
public HttpEntity<?> addComent(@CurrentUser User user, @RequestParam UUID productId,@RequestParam String text){
        ApiResponse response=comentaryaService.addComentarya(user, productId, text);
        return ResponseEntity.ok(response);
    }
}
