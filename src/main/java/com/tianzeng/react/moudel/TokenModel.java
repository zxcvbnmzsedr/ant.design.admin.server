package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * Created by tianzeng on 17-4-19.
 */
@Data
@AllArgsConstructor
public class TokenModel  {

    // 用户 id
    private Long userId;
    // 随机生成的 uuid
    private String token;
}
