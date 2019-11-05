package com.xbcxs.common.extend.test;

import com.xbcxs.common.extend.HttpClientManager;
import com.xbcxs.common.extend.HttpClientManagerImpl;
import org.apache.http.client.fluent.Content;

/**
 * @author xiaosh
 * @date 2019/10/14
 */
public class AbcExtenderHttpImpl implements AbcExtender {

    private HttpClientManager getHttpClientManager() {
        return new HttpClientManagerImpl();
    }

    @Override
    public Object method1() {
        Content content = getHttpClientManager().get();
        // TODO 数据格式转换
        Object obj = content;
        return obj;
    }

}
