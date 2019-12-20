package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class Connection {

    long bornTime;

    public Connection() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bornTime = System.currentTimeMillis();
    }



}
