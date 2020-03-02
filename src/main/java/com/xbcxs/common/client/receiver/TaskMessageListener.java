package com.xbcxs.common.client.receiver;

import com.xbcxs.common.client.receiver.jar.MRBindEvent;
import com.xbcxs.common.client.receiver.jar.MRBindExchange;
import com.xbcxs.common.client.receiver.jar.MRMessageListener;

@MRBindExchange(value = "TASK_APPROVAL")
public class TaskMessageListener extends MRMessageListener {

    @MRBindEvent(value = "processStart")
    public void tt(){

    }


}
