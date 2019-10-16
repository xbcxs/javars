package com.xbcxs.common.extend;

import com.alibaba.fastjson.JSONObject;
import com.xbcxs.common.exception.DataNotFoundException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author xiaosh
 * @date 2019/10/14
 */
public class HttpClientManagerImpl implements HttpClientManager{

    private static Logger log = LoggerFactory.getLogger(HttpClientManagerImpl.class);

    private static ExtendDao getExtendManager(){
        return new ExtendDaoImpl();
    }

    @Override
    public Content get() {
        String interfaceName = getInterfaceName();
        String methodName = getMethodName();
        log.debug("interfaceName:{}。 methodName:{}", interfaceName, methodName);

        ExtendDO extendDo = getExtendManager().getExtendDO(interfaceName);
        String url = String.valueOf(extendDo.getMethods().get(methodName));
        // TODO URL 占位符替换
        Content content = null;
        try {
            content = Request.Get(url).execute().returnContent();
            log.debug("content:" + content);
        } catch (IOException e) {
            throw new DataNotFoundException(e.getMessage() + "--httpClient请求失败，[参数URL]：" + url);
        }
        return content;
    }

    @Override
    public Content get(JSONObject params) {

        return null;
    }

    @Override
    public Content post() {

        return null;
    }

    @Override
    public Content post(JSONObject params) {

        return null;
    }

    /**
     * 得到调用栈中[3]的方法名字
     * @return
     */
    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    /**
     * 得到调用栈中[3]的类的接口[0]名字
     * @return
     */
    private static String getInterfaceName() {
        String interfaceName = null;
        try {
            interfaceName = Class.forName(Thread.currentThread().getStackTrace()[3].getClassName()).getInterfaces()[0].getName();
        } catch (ClassNotFoundException e) {
            throw new DataNotFoundException("Class.forName(interfaceName),interfaceName：" + interfaceName + "不存在");
        }
        return interfaceName;
    }

}
