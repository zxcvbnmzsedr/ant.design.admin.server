package com.tianzeng.react.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by tianzeng on 17-4-19.
 */
public class Assert {
    public static void notNull(Object object, String message){
        if(object == null) {
            throw new MyException(message, HttpStatus.BAD_REQUEST);
        }
    }
    public static void throwException(String message) throws MyException {
        throw new MyException(message,HttpStatus.BAD_REQUEST);
    }
    public static void notEmpty(Object array, String message) throws MyException {
        if(ObjectUtils.isEmpty(array)) {
            throw new MyException(message,HttpStatus.BAD_REQUEST);
        }
    }

}
