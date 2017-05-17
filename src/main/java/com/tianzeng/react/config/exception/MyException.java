package com.tianzeng.react.config.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by tianzeng on 17-4-19.
 */
public class MyException extends RuntimeException{

    private HttpStatus httpStatus;
    public MyException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
