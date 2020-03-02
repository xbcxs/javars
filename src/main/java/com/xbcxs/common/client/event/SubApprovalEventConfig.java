package com.xbcxs.common.client.event;

import com.xbcxs.common.client.event.jar.ApprovalEventConfig;

public class SubApprovalEventConfig extends ApprovalEventConfig {

    @Override
    public String getReceiveAddress() {
        return "AAA";
    }
}
