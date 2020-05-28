package com.xbcxs.lazymq.consumer;

import java.util.List;

/**
 * @author xiaosh
 * @date 2020/5/18
 */
public interface MessageConsumerService {

    List<MessageConsumer> getMessageConsumer(String topicId);
}
