package com.taoqy.controller;

import com.taoqy.bean.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2018/12/17
 * @see [相关类/方法]
 */
@RestController
@RequestMapping("/eureka")
public class EurekaController {

    @RequestMapping("/hello")
    @ResponseBody
    public ResponseMessage sayHello(){
        return new ResponseMessage(HttpStatus.OK, "你好，我是eurekaClient");
    }
}
