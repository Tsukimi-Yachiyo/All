package com.yachiyo.service;

import com.yachiyo.dto.LoginRequest;
import com.yachiyo.dto.RegisterRequest;
import com.yachiyo.result.Result;
import io.swagger.v3.oas.annotations.media.Schema;

public interface AuthService {

    /**
     * 登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    @Schema(description = "登录请求")
    public Result<String> Login(LoginRequest loginRequest);

    /**
     * 注册
     * @param registerRequest 注册请求
     * @return 注册结果
     */
    @Schema(description = "注册请求")
    public Result<String> Register(RegisterRequest registerRequest);
}
