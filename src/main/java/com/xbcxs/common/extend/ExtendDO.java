package com.xbcxs.common.extend;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xiaosh
 * @date 2019/10/14
 */
public class ExtendDO {

    private String interfaceName;
    private String implClassName;
    private String type;
    private JSONObject methods;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getImplClassName() {
        return implClassName;
    }

    public void setImplClassName(String implClassName) {
        this.implClassName = implClassName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getMethods() {
        return methods;
    }

    public void setMethods(JSONObject methods) {
        this.methods = methods;
    }
}
