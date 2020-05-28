package com.xbcxs.lazymq.broker;

/**
 * @author xiaosh
 * @date 2020/5/18
 */
public interface MessageBroker {

    void senderMessage(String topicId, String message);

}
