package com.taoqy.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/3
 * @see [相关类/方法]
 */
@Component
@RabbitListener(queues = "hello")
public class MessageConsumerA {

    @RabbitHandler
    public void process(String message){
        System.out.println("MessageConsumerA: "+ message);
    }
}
