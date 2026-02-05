package com.xyz.auth_service.controller;

import com.xyz.auth_service.entity.User;
import com.xyz.auth_service.service.AuthService;
import com.xyz.auth_service.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public User signup(@RequestParam String username, @RequestParam String password) {
        return authService.signup(username, password);
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            return jwtUtil.generateToken(username);
        } catch (AuthenticationException e) {
            return "Invalid username or password";
        }
    }
}
