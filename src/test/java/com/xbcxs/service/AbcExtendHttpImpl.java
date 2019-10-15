package com.xbcxs.service;

import com.xbcxs.extend.HttpClientManager;
import com.xbcxs.extend.HttpClientManagerImpl;

/**
 * @author xiaosh
 * @date 2019/10/14
 */
public class AbcExtendHttpImpl implements AbcExtend {

    private HttpClientManager getHttpClientManager(){
        return new HttpClientManagerImpl();
    }

    @Override
    public Object event1() {
        return getHttpClientManager().get();
    }

}
