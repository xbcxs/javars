package com.xbcxs.common.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class FanoutProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName) {
        String msg = "my_fanout_msg:" + new Date();
        System.out.println(msg + ":" + msg);
        //RabbitMQ将会忽略第二个参数,把消息分发给所有的队列(每个队列都有消息!)
        amqpTemplate.convertAndSend("fanoutExchange", queueName, msg);
    }
}