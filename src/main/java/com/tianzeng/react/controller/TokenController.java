package com.tianzeng.react.controller;


import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.MyException;
import com.tianzeng.react.moudel.Result;
import com.tianzeng.react.moudel.TokenModel;
import com.tianzeng.react.moudel.User;
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianzeng on 2017-03-26.
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping (method = RequestMethod.POST)
    public Result login (@RequestParam String username, @RequestParam String password,String token) throws MyException {
        Assert.notEmpty(username,"用户名不能为空");
        Assert.notEmpty(password,"用户密码不能为空");
//        User user = userService.findByName(username);
        User user = null;
        Assert.notEmpty(user,"用户尚未注册");
        if(!user.getPassword().equals(password)){
            Assert.throwException("密码错误");
        }
        if(token != null){
            TokenModel tokenModel = new TokenModel(user.getId(),token);
            if(tokenService.checkToken(tokenModel)){
                return new Result(true,"登陆成功");
            }
        }
        return new Result(true,"登陆成功",tokenService.createToken(user.getId()));
    }

}
