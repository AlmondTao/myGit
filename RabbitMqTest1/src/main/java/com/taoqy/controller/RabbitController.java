package com.taoqy.controller;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.taoqy.sender.MessageSenderA;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/3
 * @see [相关类/方法]
 */
@RestController()
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private MessageSenderA messageSenderA;

    @RequestMapping("/hello")
    public void hello(){
        messageSenderA.sendMessage();
    }



}
