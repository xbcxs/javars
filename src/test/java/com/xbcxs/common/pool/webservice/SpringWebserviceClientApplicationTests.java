package com.xbcxs.common.pool.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebserviceClientApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8889/jrs/webservice/webServiceTest?wsdl");
        Object[] objects = new Object[0];
        try {
            objects = client.invoke("getMsg", "hello，我是客户端哦！");
            System.out.println("返回数据:" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}