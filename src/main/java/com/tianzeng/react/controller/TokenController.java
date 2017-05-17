package com.tianzeng.react.controller;

import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.moudel.TokenModel;
import com.tianzeng.react.moudel.User;
import com.tianzeng.react.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tianzeng on 2017/5/17.
 */
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PutMapping("/")
    public TokenModel getToken(@RequestBody User user){
        Assert.notEmpty(user.getUsername(),"用户名不能为空");
        Assert.notEmpty(user.getPassword(),"密码不能为空");
        User userResult = userRepository.findByUsername(user.getUsername());
        Assert.notNull(user,"没有查询到用户名");
        boolean match;
        match = user.getPassword().equals(userResult.getPassword());
        TokenModel tokenModel = null;
        if(match){
            tokenModel = tokenService.createToken(userResult);
        }else {
            Assert.throwException("用户名密码错误");
        }
        return tokenModel;
    }
}
