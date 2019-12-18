package com.xbcxs.common.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author xiaosh
 * @date 2019/12/12
 */
public class PoolFactory {

    private static final Logger log = LoggerFactory.getLogger(PoolFactory.class);

    private static ExecutorService threadPool;

    /** 公平锁、线程安全、阻塞队 */
    public static LinkedBlockingQueue<Connection> availableConnectionQueue;

    /** 使用中的连接对象 */
    public static AtomicInteger activeConnectionCount;

    /** 连接池相关的配置 */
    private static PoolConfig poolConfig = PoolConfig.getConfig();

    static {
        availableConnectionQueue = new LinkedBlockingQueue(poolConfig.getMaxConnectionCount());
        activeConnectionCount = new AtomicInteger(0);
        threadPool = new ThreadPoolExecutor(poolConfig.getSparedConnectionCount(), poolConfig.getMaxConnectionCount(), 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        cycleDecrementConnection();
    }

    /**
     * 轮训减少连接数
     */
    private static void cycleDecrementConnection() {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(() -> {
            decrementConnection();
        }, 100, poolConfig.getConnectionCheckInterval(), TimeUnit.MILLISECONDS);
    }

    /**
     * 减少连接数
     */
    private static void decrementConnection() {
        // 当前总对象数 > poolConfig:minConnectionCount且availableConnectionCount > poolConfig:sparedConnectionCount则缩减连接
        boolean isDecrement = availableConnectionQueue.size() + activeConnectionCount.get() > poolConfig.getMinConnectionCount() && availableConnectionQueue.size() > poolConfig.getSparedConnectionCount();
        if (isDecrement) {
            while (availableConnectionQueue.size() > poolConfig.getSparedConnectionCount()) {
                Connection evictConn = availableConnectionQueue.iterator().next();
                // 多余的连接如果超期则移除
                if (System.currentTimeMillis() - evictConn.bornTime > poolConfig.getConnectionLifetime()) {
                    availableConnectionQueue.poll();
                }
            }
        }
    }

    /**
     * 备用连接不够时增加连接数
     */
    private static void incrementConnection() {
        // 当前总对象数 < poolConfig:maxConnection且availableConnectionCount <  poolConfig:sparedConnectionCount则增加连接
        if (availableConnectionQueue.size() + activeConnectionCount.get() < poolConfig.getMaxConnectionCount()) {
            int incrementCount = poolConfig.getSparedConnectionCount() - availableConnectionQueue.size();
            for(int i = 0; i < incrementCount; i++){
                threadPool.execute(() -> {
                    Connection conn = new Connection();
                    availableConnectionQueue.offer(conn);
                });
            }
        }
    }

    /**
     * 获取一个连接
     * @return
     */
    public static Connection getConnection() {
        incrementConnection();
        Connection conn = null;
        try {
            conn = availableConnectionQueue.poll(poolConfig.getAcquireConnectionTimeout(), TimeUnit.MICROSECONDS);
            if(conn != null){
                activeConnectionCount.incrementAndGet();
            } else {
                new RuntimeException("无可用连接！");
            }
        } catch (InterruptedException e) {
            log.error("从队列中获取可用连接失败！", e);
        }
        return conn;
    }

    public static void queuePrint() {
        Iterator<Connection> iterator = PoolFactory.availableConnectionQueue.iterator();
        System.out.println("--------------start");
        while (iterator.hasNext()) {
            System.out.println("bornTime:" + iterator.next().bornTime);
        }
        System.out.println("--------------end");
    }

}
