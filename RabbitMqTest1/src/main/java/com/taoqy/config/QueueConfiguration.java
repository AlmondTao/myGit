package com.taoqy.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/1/3
 * @see [相关类/方法]
 */
@Configuration
public class QueueConfiguration {

    final static String queueName = "hello";

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }

    public Queue userQueue(){
        return new Queue("user");
    }

//    ============以下是验证topic Exchange（主题模式）的队列============

    @Bean
    public Queue topicMessageA(){
        return new Queue("topic.A");
    }

    @Bean
    public Queue topicMessageB(){
        return new Queue("topic.B");
    }
//    ============以上是验证topic Exchange（主题模式）的队列============


//    ============以下是验证Fanout Exchange（广播模式）的队列============

    @Bean
    public Queue fanoutMessageA(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanoutMessageB(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanoutMessageC(){
        return new Queue("fanout.C");
    }
//    ============以上是验证Fanout Exchange（广播模式）的队列============


//    ============以上是验证Fanout Exchange（广播模式）的队列============


    //创建topic交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    //创建fanout交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    //ctrl+alt+b查看实现类
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    /**
     * 将topicMessageB与topicExchange绑定，bing_key为topic.#模糊匹配
     * @param topicMessageB
     * @param topicExchange
     * @return
     */
    @Bean
    public Binding bindingTopicMessageB(Queue topicMessageB, TopicExchange topicExchange){
        return BindingBuilder.bind(topicExchange).to(topicExchange).with("topic.#");
    }


    @Bean
    public Binding bindingFanoutMessageA(Queue fanoutMessageA, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutMessageA).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutMessageB(Queue fanoutMessageB, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutMessageB).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutMessageC(Queue fanoutMessageC, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutMessageC).to(fanoutExchange);
    }

}
