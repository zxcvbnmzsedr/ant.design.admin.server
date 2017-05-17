package com.tianzeng.react.service;

import com.tianzeng.react.common.Config;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Role;
import com.tianzeng.react.moudel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by tianzeng on 2017/5/17.
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
        if(!redisTemplate.boundValueOps (String.valueOf(user.getId())).get().equals(token)){
            Assert.throwException("验证失败");
        }
        boolean hasPerssion = false;
        Set<Role> roleSet = user.getRoles();
        for(Role role:roleSet){
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission:permissions){
                // 先匹配URL
                if(requestURI.startsWith(permission.getUrl())){
                    // 再匹配是否拥有权限
                    if(permission.getMethod().equals("*")||permission.getMethod().contains(method)){
                        // 拥有权限
                        hasPerssion = true;
                    }
                }
            }
        }

        if(!hasPerssion){
            Assert.throwException("没有权限");
        }
    }
}
