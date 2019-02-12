package com.taoqy;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/2/2
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RabbitMqTest {

    public Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("192.168.139.129");
        connectionFactory.setPort(5672);
        Connection connection = null;
        connection = connectionFactory.newConnection();

        return connection;
    }

    @Test
    public void sender() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        //创建通道
        Channel channel = connection.createChannel();

        //声明队列{参数：1.队列名，2，是否持久化，3.是否独占模式，4.消费者断开连接时是否删除队列，5.消息其他参数}
        channel.queueDeclare("队列一", false, false, false, null);
        //发送当前时间
        String format = String.format("当前时间：%s", new Date().getTime());
        //发送内容{参数：1.交换机名称 ""使用rabbit默认的交换器，2.队列名称，3.消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘
        // 4.消息主体}
        //
        channel.basicPublish("", "队列一", null, format.getBytes());
        System.out.println("已发送消息"+format);
//        channel.close();
        //程序结束会自当关闭连接

//        connection.close();
//        Thread.sleep(10000);

    }

    @Test
    public void consumer() throws IOException, TimeoutException {
        Connection connection = getConnection();

        Channel channel = connection.createChannel();

        //声明队列{参数：1.队列名，2，是否持久化，3.是否独占模式，4.消费者断开连接时是否删除队列，5.消息其他参数}
        channel.queueDeclare("队列一", false, false, false, null);
        //创建订阅器
        channel.basicConsume("队列一", false, "",new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routingKey = envelope.getRoutingKey();//队列名称
                String contentType = properties.getContentType();//内容类型
                String s = new String(body, "utf-8");
                System.out.println("消息正文:"+s);
                //手动确认消息{参数：1.该消息的index;2.手否批量应答，true批量，确认小于index的消息}
                channel.basicAck(envelope.getDeliveryTag(),false );
            }
        });

    }
}
