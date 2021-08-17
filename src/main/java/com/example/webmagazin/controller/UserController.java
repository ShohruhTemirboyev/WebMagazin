package com.example.webmagazin.controller;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.*;
import com.example.webmagazin.repository.UserRepository;
import com.example.webmagazin.security.JwtTokenProvider;
import com.example.webmagazin.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public HttpEntity<?> addUser(@RequestBody ReqUser reqUser){
        ApiJwtRespons response=userService.saveUser(reqUser);
        if (response.isSuccess()){
        JwtRespons jwtRespons=getJwtRespons(reqUser.getPhoneNumber(),reqUser.getPassword());
        response.setToken(jwtRespons.getToken());
        response.setTokenType(jwtRespons.getTokenType());}
        return ResponseEntity.status(response.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin){
        return ResponseEntity.ok(getJwtRespons(reqLogin.getPhoneNumber(), reqLogin.getPassword()));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getUser(@PathVariable UUID id){
       User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        return ResponseEntity.ok(userService.getUser(user));
    }
    @GetMapping("/getUsers")
    public HttpEntity<?> getUsers(){
      return ResponseEntity.ok(userRepository.findAll());
    }

    public JwtRespons getJwtRespons(String phoneNumber,String password){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtRespons(jwtTokenProvider.generateToken(authentication));
    }
}
