package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class Connection {

    long bornTime;

    public Connection(){
        bornTime = System.currentTimeMillis();
    }

    public void close(){
        PoolFactory.availableConnectionQueue.offer(this);
        PoolFactory.activeConnectionCount.decrementAndGet();
    }

}
