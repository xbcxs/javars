package com.xbcxs.extend;

import com.alibaba.fastjson.JSONObject;
import com.xbcxs.common.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public JSONObject get() {
        String interfaceName = getInterfaceName();
        String methodName = getMethodName();
        log.debug("interfaceName:{}", interfaceName);
        log.debug("methodName:{}", methodName);

        ExtendDO extendDo = getExtendManager().getExtendDO(interfaceName);
        String url = String.valueOf(extendDo.getMethods().get(methodName));
        // TODO URL 占位符替换

        // TODO 执行httpClient GET

        return null;
    }

    @Override
    public JSONObject get(JSONObject params) {

        return null;
    }

    @Override
    public JSONObject post() {

        return null;
    }

    @Override
    public JSONObject post(JSONObject params) {

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
