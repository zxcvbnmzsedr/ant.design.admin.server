package com.tianzeng.react.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianzeng on 2017/5/17.
 */
public class Config {
    // 需要过滤的地址
    public static Set<String> URUSET;
    static {
        URUSET = new HashSet<>();
        URUSET.add("/api/v1.0/token/**");
    }
}
