package com.xbcxs.common.extend;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.fluent.Content;

/**
 * @author xiaosh
 * @date 2019/10/15
 */
public interface HttpClientManager {

    Content get() ;

    Content get(JSONObject params);

    Content post();

    Content post(JSONObject params);
}
