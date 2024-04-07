package com.hcc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/auth")
public class AuthorityController {


    @PostMapping ("login")
    public ResponseEntity<?> login() {
        return null;
    }

    @PostMapping ("logout")
    public ResponseEntity<?> logout() {
        return null;
    }

    @PostMapping ("validate")
    public ResponseEntity<?> validate() {
        return null;
    }

    @GetMapping ("userinfo")
    public ResponseEntity<?> userinfo() {
        return null;
    }

}
