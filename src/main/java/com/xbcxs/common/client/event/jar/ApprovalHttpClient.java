package com.xbcxs.common.client.event.jar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = ApprovalHttpClient.APPROVAL_HTTP_URI)
public class ApprovalHttpClient {

    private static Logger log = LoggerFactory.getLogger(ApprovalHttpClient.class);

    public static final String APPROVAL_HTTP_URI = "eapclient/approval/event";

    /**
     * eapclient/approval/event?approvalModel=x&approvalEvent=y
     */
    @RequestMapping("{approvalModel}/{approvalEvent}")
    public void execute(@PathVariable("approvalModel") String approvalModel, @PathVariable("approvalEvent") String approvalEvent, HttpServletRequest request) throws Exception {
        log.debug("approvalModel:{},approvalEvent:{}", approvalModel, approvalEvent);

        ApprovalEventContext approvalEventContext = new ApprovalEventContext();
        approvalEventContext.setApprovalModel(approvalModel);
        approvalEventContext.setApprovalEvent(approvalEvent);
        approvalEventContext.setApprovalObjectId(request.getParameter("approvalObjectId"));
        approvalEventContext.setExtendedParameters("");

        Class cls = ApprovalEventRegister.approvalEventModelClassMap.get(approvalModel);
        cls.getMethod(approvalEvent, ApprovalEventContext.class).invoke(cls.newInstance(), approvalEventContext);
    }

}
