package com.tianzeng.react.controller;

import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.MyException;
import com.tianzeng.react.dao.PermissionRepository;
import com.tianzeng.react.dao.RolesRepository;
import com.tianzeng.react.dao.SourceRepository;
import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.enums.SourcePermissions;
import com.tianzeng.react.interfaces.CheckRole;
import com.tianzeng.react.moudel.*;
import com.tianzeng.react.service.RoleService;
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianzeng on 17-4-21.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RoleService roleService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody User userInfo, HttpServletResponse response) throws MyException {
        String userName = userInfo.getUsername();
        String password = userInfo.getPassword();
        Assert.notEmpty(userName,"用户名为空");
        Assert.notEmpty(password,"密码为空");
        User user = userService.findByUsername(userName);
        Assert.notNull(user,"用户名不存在");
        if(!password.equals(user.getPassword())){
            Assert.throwException("用户或者密码不正确");
        }
        response.addCookie(new Cookie("userName",userName));
        return new Result(200,true,"登陆成功",tokenService.createToken(user.getUserId()));
    }

    /**
     * 修改用户
     */
    @PutMapping("/{id}")
    public Result roles(@PathVariable("id") Long id,@RequestBody User user){
        user.setUserId(id);
        Result result = new Result();
        List<Role> roles = new ArrayList<>();
        Role role = roleService.findByDescription(user.getRolesDescribe());
        Assert.notNull(role,"不存在该角色");
        roles.add(role);
        user.setRoles(roles);
        user.setRolesDescribe(role.getDescription());
        userService.save(user);
        result.setMessage("更新权限成功");
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }
    /**
     * 删除用户
     */


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }


}
