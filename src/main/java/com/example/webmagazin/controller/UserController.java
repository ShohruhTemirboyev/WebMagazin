package com.example.webmagazin.controller;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.payloat.*;
import com.example.webmagazin.repository.UserRepository;
import com.example.webmagazin.security.CurrentUser;
import com.example.webmagazin.security.JwtTokenProvider;
import com.example.webmagazin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
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
        if (response.getCode()==200){
        JwtRespons jwtRespons=getJwtRespons(reqUser.getPhoneNumber(),reqUser.getPassword());
        response.setToken(jwtRespons.getToken());
        response.setTokenType(jwtRespons.getTokenType());}
        return ResponseEntity.ok(response);
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
    @PutMapping("/editUser")
    public HttpEntity<?> getEdit(@RequestBody ReqUser reqUser, @CurrentUser User user){
         ApiResponse response=userService.editUser(reqUser, user);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/add/product/{productId}")
    public HttpEntity<?> addLikedProduct(@CurrentUser User user,@PathVariable UUID productId){
    ApiResponse response=userService.addLikedProduct(user,productId);
    return ResponseEntity.ok(response);
    }

    @PostMapping("/remove/product/{productId}")
    public HttpEntity<?> removeProduct(@CurrentUser User user,@PathVariable UUID productId){
        ApiResponse response=userService.remoweLikedProduct(user,productId);
        return ResponseEntity.ok(response);
    }

}
