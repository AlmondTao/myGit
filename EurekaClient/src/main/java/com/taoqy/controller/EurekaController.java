package com.taoqy.controller;

import com.taoqy.bean.ResponseMessage;
import com.taoqy.service.EurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

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

    @Autowired
    private EurekaService eurekaService;

    @RequestMapping("/hello")
    @ResponseBody
    public ResponseMessage sayHello() throws InterruptedException {
//        Thread.sleep(1000L);

        return new ResponseMessage(HttpStatus.OK, "你好，我是eurekaClient");
    }

    @RequestMapping("/exception")
    @ResponseBody
    public ResponseMessage exception() throws SQLException {
        if(0==0){
            throw new SQLException();
        }
        return new ResponseMessage(HttpStatus.BAD_REQUEST, "11111");
    }
}
