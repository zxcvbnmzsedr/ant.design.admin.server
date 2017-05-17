package com.tianzeng.react.config.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by tianzeng on 17-4-19.
 */
@Data
public class ErrorInfo<T> {
    private String message;
    private String url;
}
