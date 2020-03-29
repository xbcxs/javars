package com.xbcxs.common.eventclient.receiver;

import com.xbcxs.common.eventclient.receiver.jar.MRBindEvent;
import com.xbcxs.common.eventclient.receiver.jar.MRBindExchange;
import com.xbcxs.common.eventclient.receiver.jar.MRMessageListener;

@MRBindExchange(value = "TASK_APPROVAL")
public class TaskMessageListener extends MRMessageListener {

    @MRBindEvent(value = "processStart")
    public void tt(){

    }


}
