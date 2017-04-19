package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by tianzeng on 17-4-19.
 */
@Data
@AllArgsConstructor
public class TokenModel  {
    // 用户 id
    private long userId;
    // 随机生成的 uuid
    private String token;
}
