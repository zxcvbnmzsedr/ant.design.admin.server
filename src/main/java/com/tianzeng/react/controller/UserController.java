package com.tianzeng.react.controller;

import com.tianzeng.react.moudel.Result;
import com.tianzeng.react.moudel.StaffDataMoudel;
import com.tianzeng.react.moudel.User;
import com.tianzeng.react.service.StaffDataService;
import com.tianzeng.react.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tianzeng on 2017-03-26.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private StaffDataService staffDataService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result listAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size){
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(staffDataService.listStaffData(page,size));
        return result;
    }

    @DeleteMapping("/remove/{jobNumber}")
    public Result remove(@PathVariable("jobNumber")String jobNumber){
        return staffDataService.remove(jobNumber);

    }

    @PostMapping("/query")
    public Result login(@RequestBody User user) {
        logger.info("接收到用户信息"+user.toString());
        if(userService.findUser(user)!= null){
            return new Result(true,"登录成功");
        }
        return new Result(false,"登录失败");
    }
    @PostMapping("/create")
    public Result create(@RequestBody StaffDataMoudel staffDataMoudel){
        Result result = new Result();
        User user = new User();
        user.setPassword("123456");
        user.setUsername(staffDataMoudel.getJobNumber());
        staffDataService.save(staffDataMoudel);
        user.setStaffDataMoudel(staffDataMoudel);
        userService.saveOne(user);
        result.setSuccess(true);
        return result;
    }
}
