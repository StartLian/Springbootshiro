package com.jessica.shirotest.filter;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    public String ErrorHandler(AuthorizationException e) {
       System.out.println("没有通过权限验证！"+ e.toString());
        return "my_error";
    }
}
