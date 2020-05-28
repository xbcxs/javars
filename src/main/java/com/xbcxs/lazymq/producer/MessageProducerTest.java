package com.xbcxs.lazymq.producer;

import com.xbcxs.lazymq.broker.MessageBroker;
import com.xbcxs.lazymq.broker.MessageBrokerImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaosh
 * @date 2020/5/18
 */
public class MessageProducerTest {

    public static void main(String[] args) {
        // 构建消息
        Map message = new HashMap<String, String>();
        message.put("id", System.currentTimeMillis());
        message.put("name", "Mike Xiao");

        // 发送消息
        MessageBroker messageBroker = new MessageBrokerImpl();
        messageBroker.senderMessage("", message.toString());
    }
}
