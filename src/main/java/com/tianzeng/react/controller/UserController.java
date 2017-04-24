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
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private SourceRepository sourceRepository;
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
    @PostMapping("/addPer")
    public Result add(String token){
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        // 设置用户角色
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setDescription("管理员");
        role.setName("admin");
        roles.add(role);
        // 设置权限
        List<Permission> permissions = new ArrayList<>();
        Permission permission = new Permission();
        permission.setDescription("增加权限");
        permission.setPermissions(SourcePermissions.CREATE);
        permissions.add(permission);

        // 设置资源
        List<Source> sources = new ArrayList<>();
        Source source = new Source();
        source.setDescription("用户删除");
        source.setName("/user/delete");
        sources.add(source);

        sourceRepository.save(sources);

        permission.setSource(sources);

        permissionRepository.save(permissions);

        role.setPermissions(permissions);

        rolesRepository.save(role);

        user.setRoles(roles);
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/addRole")
    public Result addRole(String token){
        // 设置用户角色

        Role role = new Role();
        role.setDescription("管理员");
        role.setName("admin");

       rolesRepository.save(role);
       return null;
    }



}
