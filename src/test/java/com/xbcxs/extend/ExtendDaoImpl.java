package com.xbcxs.extend;

import com.alibaba.fastjson.JSONObject;

/**
 * Manager实现
 * @author xiaosh
 * @date 2019/10/15
 */
public class ExtendDaoImpl implements ExtendDao {

    @Override
    public ExtendDO getExtendDO(String interfaceName) {

        // 模拟获取Http实现类型数据对象
        /*ExtendDO edo = new ExtendDO();
        edo.setInterfaceName("com.xbcxs.service.AbcExtend");
        edo.setImplClassName("com.xbcxs.service.AbcExtendJarImpl");
        edo.setType(ExtendTypeEnum.JAR.toString());*/

        // 模拟获取Http实现类型数据对象
        ExtendDO edo = new ExtendDO();
        edo.setInterfaceName("com.xbcxs.service.AbcExtend");
        edo.setImplClassName("com.xbcxs.service.AbcExtendHttpImpl");
        edo.setType(ExtendTypeEnum.HTTP.toString());
        JSONObject methods = new JSONObject();
        methods.put("method1", "{KE}/aaa/bbb");
        methods.put("method2", "http://192.168.1.1:8000/p2m/aaa/bbb");
        edo.setMethods(methods);
        return edo;
    }
}
