package com.xbcxs.common.eventclient.event;

import com.xbcxs.common.eventclient.event.jar.ApprovalEventConfig;

public class SubApprovalEventConfig extends ApprovalEventConfig {

    @Override
    public String getReceiveAddress() {
        return "AAA";
    }
}
