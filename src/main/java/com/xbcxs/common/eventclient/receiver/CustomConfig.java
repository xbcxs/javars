package com.xbcxs.common.eventclient.receiver;

import com.xbcxs.common.eventclient.receiver.jar.MRConfig;

public class CustomConfig extends MRConfig {

    @Override
    public String getReceiveAddress() {
        return super.getReceiveAddress();
    }

    @Override
    public String getApprovalServerAddress() {
        return super.getApprovalServerAddress();
    }
}
