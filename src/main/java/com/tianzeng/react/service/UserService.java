package com.tianzeng.react.service;

import com.tianzeng.react.security.Permission;
import com.tianzeng.react.security.Role;
import com.tianzeng.react.security.User;
import com.tianzeng.react.security.enums.SourcePermissions;
import org.springframework.stereotype.Service;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by tianzeng on 2017/5/18.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 当前api需要的权限值
     * @param method       method
     * @param requestURI
     * @return value
     */
    public void check(long userId,String token, String method, String requestURI){
        User user = userRepository.findOne(userId);
        if(!redisTemplate.boundValueOps (String.valueOf(user.getUserId())).get().equals(token)){
            Assert.throwException("验证失败");
        }

        boolean hasPermission = false;
        Set<Role> roleSet = user.getRoles();
        for(Role role:roleSet){
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission:permissions){
                /*实在不知道URL是怎么匹配的，先截了做个假象 哈哈*/
                if(permission.getSource().getHttpUrl().endsWith("**")){
                    String sourceUrl = permission.getSource().getHttpUrl().split("/\\*\\*")[0];
                    if(requestURI.startsWith(sourceUrl)){
                        if(permission.getMethod().equals(SourcePermissions.parseCode(method))){
                            hasPermission = true;
                        }
                    }
                }
            }
        }

        if(!hasPermission){
            Assert.throwException("没有权限");
        }
    }
}
