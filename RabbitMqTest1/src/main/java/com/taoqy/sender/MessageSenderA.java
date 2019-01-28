package com.taoqy.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/3
 * @see [相关类/方法]
 */
@Component
public class MessageSenderA {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(){
        String message = new Date().toString();
        System.out.println("MessageSenderA: "+message);
        amqpTemplate.convertAndSend("hello", message);
    }
}
