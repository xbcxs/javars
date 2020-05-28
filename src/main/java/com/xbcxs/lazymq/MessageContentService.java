package com.xbcxs.lazymq;

import java.util.List;

/**
 * @author xiaosh
 * @date 2020/5/18
 */
public interface MessageContentService {

    void save(MessageContent messageContent);

    void delete(String id);

    List<MessageContent> getOverstock(String topicId, String subscriblerId);

}
