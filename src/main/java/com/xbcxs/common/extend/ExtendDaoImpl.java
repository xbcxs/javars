package com.xbcxs.common.extend;

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
        edo.setInterfaceName(interfaceName);
        edo.setImplClassName("com.xbcxs.service.AbcExtendJarImpl");
        edo.setType(ExtendTypeEnum.JAR.toString());*/

        // 模拟获取Http实现类型数据对象
        ExtendDO edo = new ExtendDO();
        edo.setInterfaceName(interfaceName);
        edo.setImplClassName("com.xbcxs.common.extend.test.AbcExtendHttpImpl");
        edo.setType(ExtendTypeEnum.HTTP.toString());
        JSONObject methods = new JSONObject();
        methods.put("method1", "https://www.baidu.com");
        methods.put("method2", "{KE}/aaa/bbb");
        edo.setMethods(methods);
        return edo;
    }
}
