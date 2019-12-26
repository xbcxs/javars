package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/24
 */
public class AConn extends Connection {

    static {
        ConnectionFactory.register(new AConn());
        System.out.println("static ...");
    }

    @Override
    public Connection getObject() {
        System.out.println("getObject ...");
        AConn ac = new AConn();
        // 初始化参数
        return ac;
    }
}
