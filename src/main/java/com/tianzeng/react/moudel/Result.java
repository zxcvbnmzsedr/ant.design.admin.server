package com.tianzeng.react.moudel;

import org.springframework.stereotype.Component;

/**
 * Created by tianzeng on 2017-03-16.
 */
@Component
public class Result {
    private String message;
    private boolean success;
    private Object obj;
    public Result(boolean success,String message) {
        this.message = message;
        this.success = success;
    }

    public Result() {
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
