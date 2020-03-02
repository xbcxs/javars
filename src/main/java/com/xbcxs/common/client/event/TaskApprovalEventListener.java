package com.xbcxs.common.client.event;

import com.xbcxs.common.client.event.jar.ApprovalEventListener;
import com.xbcxs.common.client.event.jar.ApprovalEventContext;

public class TaskApprovalEventListener extends ApprovalEventListener {

    /**
     * 绑定审批模型事件
     * @return
     */
    @Override
    protected String bindApprovalModel() {
        return "TASK_APPROVAL";
    }

    /**
     * 事件实现
     * @param context
     */
    @Override
    public void processStart(ApprovalEventContext context) {
        System.out.println("TaskApprovalEventListener.processStart...");
    }
}
