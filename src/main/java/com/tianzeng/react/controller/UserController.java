package com.tianzeng.react.controller;

import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.MyException;
import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.interfaces.CheckRole;
import com.tianzeng.react.moudel.Result;
import com.tianzeng.react.moudel.TokenModel;
import com.tianzeng.react.moudel.User;
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianzeng on 17-4-21.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestParam String userName, String password,HttpServletResponse response) throws MyException {
        Assert.notEmpty(userName,"用户名为空");
        Assert.notEmpty(password,"密码为空");
        User user = userService.findByUsername(userName);
        Assert.notNull(user,"用户名不存在");
        if(!password.equals(user.getPassword())){
            Assert.throwException("用户或者密码不正确");
        }
        response.addCookie(new Cookie("userName",userName));
        return new Result(true,"登陆成功",tokenService.createToken(user.getUserId()));
    }

    @PostMapping("/delete")
    @CheckRole(value = "ddddd")
    public Result delete(String token){
        return null;
    }
}
