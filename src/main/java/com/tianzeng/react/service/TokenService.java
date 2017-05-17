package com.tianzeng.react.service;

import com.tianzeng.react.config.Constants;
import com.tianzeng.react.moudel.TokenModel;
import com.tianzeng.react.moudel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by tianzeng on 2017/5/17.
 */
@Service
public class TokenService {
    @Autowired
    private RedisTemplate redisTemplate;

    public TokenModel createToken( User user){
        String token = UUID.randomUUID().toString().replace ("-", "");
        TokenModel tokenModel = new TokenModel(user.getId(),token);
        redisTemplate.boundValueOps(String.valueOf(user.getId())).set (token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return tokenModel;
    }
    public boolean checkToken (TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = (String) redisTemplate.boundValueOps (String.valueOf(model.getUserId())).get();
        if (token == null || !token.equals (model.getToken ())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
        redisTemplate.boundValueOps (String.valueOf(model.getUserId())).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    public void deleteToken (long userId) {
        redisTemplate.delete (userId);
    }
}
