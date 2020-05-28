package com.xbcxs.lazymq.broker;

import com.xbcxs.lazymq.*;
import com.xbcxs.lazymq.MessageContent;
import com.xbcxs.lazymq.consumer.MessageConsumer;
import com.xbcxs.lazymq.consumer.MessageConsumerService;
import com.xbcxs.lazymq.consumer.MessageConsumerServiceImpl;
import com.xbcxs.lazymq.consumer.MessageConsumerTest;

import java.util.List;

/**
 * @author xiaosh
 * @date 2020/5/18
 */
public class MessageBrokerImpl implements MessageBroker {

    private MessageContentService queueService = new MessageContentServiceImpl();
    private MessageConsumerService subscriberService = new MessageConsumerServiceImpl();

    @Override
    public void senderMessage(String topicId, String message) {

        // 遍历该主题的所有订阅者
        List<MessageConsumer> consumerList = subscriberService.getMessageConsumer(topicId);
        String subscriberId;
        for (MessageConsumer consumer : consumerList) {
            subscriberId = consumer.getId();
            // 查询并处理积压消息
            List<MessageContent> queueList = queueService.getOverstock(topicId, subscriberId);
            for (MessageContent messageContent : queueList) {
                // 向订阅者发送积压消息
                boolean  flag = sendMessage(messageContent.getContent(), false);
                if(!flag){
                    break;
                }
                // 删除队列库中的积压消息
                queueService.delete(messageContent.getId());
            }
            // 向订阅者发送本次消息
            sendMessage(message, true);
        }

    }

    /***
     * 向消费者发送消息
     */
    private boolean sendMessage(String message, boolean flag) {

        boolean sendSuccess = true;
        try {
            MessageConsumerTest.consume(message);
        } catch (Exception e) {
            sendSuccess = false;
        }

        if(flag){
            if (sendSuccess) {
                // 将消息保存入库，下次再发送
                MessageContent messageContent = new MessageContent();
                messageContent.setContent(message);
                queueService.save(messageContent);
            }
        }

        return sendSuccess;
    }

}
