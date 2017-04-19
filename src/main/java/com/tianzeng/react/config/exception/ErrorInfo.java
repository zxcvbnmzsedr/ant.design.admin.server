package com.tianzeng.react.config.exception;

import lombok.Data;

/**
 * Created by tianzeng on 17-4-19.
 */
@Data
public class ErrorInfo<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;
    private Integer code;
    private String message;
    private String url;
    private T data;
}
