package com.yachiyo.service.Impl;

import com.yachiyo.dto.LoginRequest;
import com.yachiyo.dto.RegisterRequest;
import com.yachiyo.result.Result;
import com.yachiyo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public Result<Boolean> Login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Result<Boolean> Register(RegisterRequest registerRequest) {
        return null;
    }
}
