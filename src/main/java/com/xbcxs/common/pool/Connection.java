package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class Connection {

    long bornTimeValue;

    public Connection(){
        bornTimeValue = System.currentTimeMillis();
    }
    public void close(){
        Connection c = this;
        PoolFactory.busyList.remove(c);
        PoolFactory.leisureQueue.offer(c);
    }

}
