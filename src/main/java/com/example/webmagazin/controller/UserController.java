package com.example.webmagazin.controller;

import com.example.webmagazin.payloat.ApiJwtRespons;
import com.example.webmagazin.payloat.JwtRespons;
import com.example.webmagazin.payloat.ReqUser;
import com.example.webmagazin.security.JwtTokenProvider;
import com.example.webmagazin.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

@PostMapping("/register")
public HttpEntity<?> addUser(@RequestBody ReqUser reqUser){
    ApiJwtRespons response=userService.saveUser(reqUser);
    if (response.isSuccess()){
        JwtRespons jwtRespons=getJwtRespons(reqUser.getPhoneNumber(),reqUser.getPassword());
        response.setToken(jwtRespons.getToken());
        response.setTokenType(jwtRespons.getTokenType());}
    return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);

}


    public JwtRespons getJwtRespons(String phoneNumber,String password){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtRespons(jwtTokenProvider.generateToken(authentication));
    }
}
