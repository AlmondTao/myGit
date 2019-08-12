package com.taoqy.controller;

import com.taoqy.bean.ResponseMessage;
import com.taoqy.handler.MyException;
import com.taoqy.service.EurekaService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ResponseMessage exception() throws MyException {
        if(0==0){
//            throw new SQLException();
            throw new MyException("11111");
        }
        return new ResponseMessage(HttpStatus.BAD_REQUEST, "11111");
    }

    @RequestMapping(value = "/animal",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getAnimal(){
        return new ResponseMessage(HttpStatus.OK, "获取animal成功");
    }

    @RequestMapping(value = "/animal", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage createAnimal(){
        return new ResponseMessage(HttpStatus.OK, "创建animal成功");
    }
}
