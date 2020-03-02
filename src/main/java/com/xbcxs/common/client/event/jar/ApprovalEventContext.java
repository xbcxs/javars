package com.xbcxs.common.client.event.jar;

import java.io.Serializable;

public class ApprovalEventContext implements Serializable {

    public ApprovalEventContext() {
    }

    private String approvalModel;
    private String approvalEvent;
    private String approvalObjectId;
    private String extendedParameters;

    public String getApprovalModel() {
        return approvalModel;
    }

    public void setApprovalModel(String approvalModel) {
        this.approvalModel = approvalModel;
    }

    public String getApprovalEvent() {
        return approvalEvent;
    }

    public void setApprovalEvent(String approvalEvent) {
        this.approvalEvent = approvalEvent;
    }

    public String getApprovalObjectId() {
        return approvalObjectId;
    }

    public void setApprovalObjectId(String approvalObjectId) {
        this.approvalObjectId = approvalObjectId;
    }

    public String getExtendedParameters() {
        return extendedParameters;
    }

    public void setExtendedParameters(String extendedParameters) {
        this.extendedParameters = extendedParameters;
    }
}
