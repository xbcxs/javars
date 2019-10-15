package com.xbcxs.extend;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xiaosh
 * @date 2019/10/15
 */
public interface HttpClientManager {

    JSONObject get();

    JSONObject get(JSONObject params);

    JSONObject post();

    JSONObject post(JSONObject params);
}
