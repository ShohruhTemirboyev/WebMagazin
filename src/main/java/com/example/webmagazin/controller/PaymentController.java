package com.example.webmagazin.controller;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponseModel;
import com.example.webmagazin.payloat.ReqCreateCard;
import com.example.webmagazin.payment.PaymentService;
import com.example.webmagazin.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentServic;

    @PostMapping("/card/create")
    public HttpEntity<?> createCard(@RequestBody ReqCreateCard reqCreateCard, @CurrentUser User user){
        ApiResponseModel responseModel=paymentServic.addCard(reqCreateCard,user);
        return ResponseEntity.ok(responseModel);
    }

}
