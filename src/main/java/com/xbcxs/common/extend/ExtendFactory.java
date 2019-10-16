package com.xbcxs.common.extend;

import com.xbcxs.common.exception.DataNotFoundException;

/**
 * @author xiaosh
 * @date 2019/10/12
 */
public class ExtendFactory {

    private static ExtendDao getExtendManager(){
        return new ExtendDaoImpl();
    }

    /**
     * 获取子类实现对象
     * @param interfaceCls 接口类型名
     * @return
     * @throws Exception
     */
    public static Object getExtendInstance(Class interfaceCls) {
        Object object = null;
        ExtendDO extendDo = getExtendManager().getExtendDO(interfaceCls.getName());
        if(extendDo != null){
            try {
                object = Class.forName(extendDo.getImplClassName()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new DataNotFoundException(e.getMessage() + "；[参数implClassName：]" + extendDo.getImplClassName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new DataNotFoundException(e.getMessage() + "；[参数implClassName：]" + extendDo.getImplClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new DataNotFoundException(e.getMessage() + "；[参数implClassName：]" + extendDo.getImplClassName() + "不存在！");
            }
        }
        return object;
    }

}
