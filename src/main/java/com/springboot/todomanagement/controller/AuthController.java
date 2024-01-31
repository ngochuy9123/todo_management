package com.springboot.todomanagement.controller;

import com.springboot.todomanagement.dto.LoginDto;
import com.springboot.todomanagement.dto.RegisterDto;
import com.springboot.todomanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String res = authService.register(registerDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.getUsernameOrEmail());
        System.out.println(loginDto.getPassword());
        String res = authService.login(loginDto);
        System.out.println(res);
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }
}
