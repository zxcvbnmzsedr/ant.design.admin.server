package com.tianzeng.react.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianzeng on 17-4-19.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<?> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setUrl(req.getRequestURL().toString());
        return new ResponseEntity<>(r, e.getHttpStatus());
    }
}
