package com.taoqy.handler;

import com.taoqy.bean.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/26
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@ControllerAdvice
public class EurekaControllerAdvice {

    //如果抛出的是MyException则不会走此处理器，会走myException处理器
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage exceptionHandler(Exception e){
        return new ResponseMessage(HttpStatus.BAD_REQUEST, "ExceptionHandler: "+e.getMessage());
    }


    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResponseMessage myExceptionHandler(Exception e){
        return new ResponseMessage(HttpStatus.BAD_REQUEST, "MyExceptionHandler: "+e.getMessage());
    }
}
