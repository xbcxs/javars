package com.xbcxs.common.eventclient.event.jar;

public class ApprovalEventConfig {

    private String receiveAddress;
    private String approvalServerAddress;

    public String getReceiveAddress() {

        // TODO 对接配置中心默认实现
        return receiveAddress = "MDM";
    }

    public String getApprovalServerAddress() {

        // TODO 对接配置中心默认实现
        return approvalServerAddress = "http://localhost:8889/jrs/testRegisterServerCenter/register";
    }
}
