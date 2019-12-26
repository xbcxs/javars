package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public abstract class Connection {

    long bornTime;

    public Connection() {
        try {
            Thread.sleep(5000);
            System.out.println("Connection 1000ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bornTime = System.currentTimeMillis();
    }

    public abstract Connection getObject();

}
