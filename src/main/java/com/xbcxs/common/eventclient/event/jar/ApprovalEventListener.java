package com.xbcxs.common.eventclient.event.jar;

public abstract class ApprovalEventListener {

    protected abstract String bindApprovalModel();

    @ApprovalEventAnnotation
    protected void processStart(ApprovalEventContext context) {
    }

    @ApprovalEventAnnotation
    protected void processEnd(ApprovalEventContext context) {
    }

    @ApprovalEventAnnotation
    protected void nodeStart(ApprovalEventContext context) {
    }

    @ApprovalEventAnnotation
    public void nodeEnd(ApprovalEventContext context) {
    }
}
