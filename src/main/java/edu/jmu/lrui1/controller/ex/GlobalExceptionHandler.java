package edu.jmu.lrui1.controller.ex;

import edu.jmu.lrui1.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: lrui1
 * @description
 * @date: 2023/6/17
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(NullException.class)
    public Result exception1(NullException ex) {
        log.info(ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Result ex2(UnauthenticatedException ex) {
        log.error(ex.getMessage());
        return Result.error("请先登录");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result ex3(UnauthorizedException ex) {
        log.error(ex.getMessage());
        return Result.error("无权访问");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public Result ex4(IncorrectCredentialsException ex) {
        log.error(ex.getMessage());
        return Result.error("密码错误");
    }

    @ExceptionHandler(UnknownAccountException.class)
    public Result ex5(UnknownAccountException ex) {
        log.error(ex.getMessage());
        return Result.error("账号不存在");
    }

    @ExceptionHandler(Exception.class)
    public Result finalEx(Exception ex) {
        log.error(ex.getMessage());
        return Result.error("未知异常");
    }
}
