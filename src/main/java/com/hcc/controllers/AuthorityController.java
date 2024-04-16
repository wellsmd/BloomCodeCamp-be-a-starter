package com.hcc.controllers;

import com.hcc.dto.AuthCredentialRequest;
import com.hcc.entities.User;
import com.hcc.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/auth")
public class AuthorityController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin("*")
    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody AuthCredentialRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authenticate.getPrincipal();
            user.setPassword(null);
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            token
                    )
                    .body(token);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String token, @AuthenticationPrincipal User user) {
        try {
            if (user != null) {
                boolean isTokenValid = jwtUtil.validateToken(token, user);
                return ResponseEntity.ok(isTokenValid);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (ExpiredJwtException ex) {
            return ResponseEntity.ok(false);
        }
    }

//    @CrossOrigin("*")
//    @PostMapping("/sign-up")
//    public ResponseEntity<?> signup(@RequestBody User user) throws Exception {
//        Authentication authentication;
//
//        try {
//            String encodedPassword = customPasswordEncoder.getPasswordEncoder().encode(user.getPassword());
//            user.setPassword(encodedPassword);
//            userRepo.save(user);
//            authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUsername(), encodedPassword, user.getAuthorities()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            User newUser = (User) authentication.getPrincipal();
//            newUser.setPassword(null);
//            String passwordToken = jwtUtil.generateToken(newUser);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.AUTHORIZATION, passwordToken)
//                    .body(newUser.getUsername());
//        } catch (BadCredentialsException exception) {
//            throw new Exception("Bogus credentials, dude.");
//        }
//    }
}
