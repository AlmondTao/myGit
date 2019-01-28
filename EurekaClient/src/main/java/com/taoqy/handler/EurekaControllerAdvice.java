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

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage exceptionHandler(){
        return new ResponseMessage(HttpStatus.BAD_REQUEST, "产生异常");
    }
}
