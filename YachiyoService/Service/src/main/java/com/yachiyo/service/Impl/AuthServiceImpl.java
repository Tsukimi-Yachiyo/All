package com.yachiyo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yachiyo.Config.SecuritySafeToolConfig;
import com.yachiyo.Utils.JwtUtils;
import com.yachiyo.dto.LoginRequest;
import com.yachiyo.dto.RegisterRequest;
import com.yachiyo.entity.User;
import com.yachiyo.mapper.UserMapper;
import com.yachiyo.result.Result;
import com.yachiyo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SecuritySafeToolConfig securitySafeToolConfig;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Result<String> Login(LoginRequest loginRequest) {
        try {
            User user = new User();
            user.setName(loginRequest.getUsername());
            user.setPassword(securitySafeToolConfig.md5(loginRequest.getPassword()));
            user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, user.getName())
                    .eq(User::getPassword, user.getPassword()));
            if (user == null) {
                return Result.error("400","用户名或密码错误",null);
            }
            String token = jwtUtils.generateToken((long) user.getId(), user.getName(), securitySafeToolConfig.getUnique());
            return Result.success(token, "登录成功",null);
        } catch (Exception e) {
            return Result.error("500","登录失败",e.getMessage());
        }
    }

    @Override
    public Result<String> Register(RegisterRequest registerRequest) {
        try {
            User user = new User();
            user.setName(registerRequest.getUsername());
            user.setPassword(securitySafeToolConfig.md5(registerRequest.getPassword()));
            if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, user.getName())) != null) {
                return Result.error("400","用户名已存在",null);
            }
            userMapper.insert(user);
            String token = jwtUtils.generateToken((long) user.getId(), user.getName(), securitySafeToolConfig.getUnique());
            return Result.success(token, "注册成功",null);
        } catch (Exception e) {
            return Result.error("500","注册失败",e.getMessage());
        }
    }
}
