package com.example.webmagazin.payment;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.ApiResponseModel;
import com.example.webmagazin.payloat.ReqCreateCard;
import com.example.webmagazin.repository.BalanceChangeLogRepository;
import com.example.webmagazin.repository.ExceptionRepository;
import com.example.webmagazin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BalanceChangeLogRepository balanceChangeLogRepository;
    @Autowired
    ExceptionRepository exceptionRepository;

    public ApiResponseModel addCard(ReqCreateCard card, User user){
    ApiResponseModel responseModel=new ApiResponseModel();
     try {
         RestTemplate template=new RestTemplate();


     }
     catch (Exception e){
         responseModel.setMessage("Error");
         responseModel.setCode(500);
     }
     return responseModel;
    }
}
