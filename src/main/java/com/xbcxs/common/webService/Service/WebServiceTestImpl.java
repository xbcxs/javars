package com.xbcxs.common.webservice.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Random;

/**
 *
 */
@WebService(serviceName = "webServiceTestImpl",//对外发布的服务名,
        targetNamespace = "http://service.common.weservice.xbcxs.com/", //指定你想要的名称空间，通常使用使用包名反转,最后面有一个斜线
        endpointInterface = "com.xbcxs.common.webservice.Service.WebServiceTest")
@Component
public class WebServiceTestImpl implements com.xbcxs.common.webservice.Service.WebServiceTest {

    private static final Logger log = LoggerFactory.getLogger(WebServiceTestImpl.class);

    @Override
    public String getMsg(String msg) {
        log.info("客户端发来的参数：{}", msg);
        String serviceMsg = "hello,I'm server client." + new Random().nextLong();
        return serviceMsg;
    }

}