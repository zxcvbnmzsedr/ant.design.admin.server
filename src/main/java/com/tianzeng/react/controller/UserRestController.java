package com.tianzeng.react.controller;


import com.tianzeng.react.moudel.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kaenry on 2016/9/8.
 * UserRestController
 */
@RestController
@RequestMapping("/api")
public class UserRestController {


    @RequestMapping("")
    public Result all() {
        Result result = new Result();
//        List<User> list = userService.findAll();
        result.setMessage("cccc");
        result.setObj("ddd");
        return result;
    }
}
