package com.xbcxs.common.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class PoolFactory {

    private static final Logger log = LoggerFactory.getLogger(PoolFactory.class);

    private static ThreadPoolExecutor threadPool;

    /**
     * 连接池相关的配置
     */
    private static PoolConfig poolConfig = PoolConfig.getConfig();

    /**
     * 可用连接对象池
     */
    public static LinkedBlockingQueue<Connection> availableConnectionQueue;
    /**
     * 使用中的连接对象数
     */
    public static AtomicInteger usingConnectionCount;

    /**
     * 需要保持的可用数
     */
    private static final int sparedCount = poolConfig.getSparedConnectionCount();
    /**
     * 最大连接数
     */
    private static final int maxCount = poolConfig.getMaxConnectionCount();

    static {
        availableConnectionQueue = new LinkedBlockingQueue(poolConfig.getMaxConnectionCount());
        usingConnectionCount = new AtomicInteger(0);
        Connection conn = new Connection();
        availableConnectionQueue.offer(conn);

        threadPool = new ThreadPoolExecutor(5, 100, 60000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());
        cycleDecrementConnection();
    }

    /**
     * 轮训检测可用连接是否过期
     */
    private static void cycleDecrementConnection() {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleWithFixedDelay(() -> {
            decrementConnection();
        }, 1000, poolConfig.getConnectionCheckInterval(), TimeUnit.MILLISECONDS);
    }

    /**
     * 减少多余超期连接数
     */
    private static void decrementConnection() {
        log.info("轮训清除超期连接数...availableConnectionQueue:{},usingConnectionCount{},ThreadOnWayCount{},当前总数：{}", availableConnectionQueue.size(), usingConnectionCount.get(), threadPool.getActiveCount(), availableConnectionQueue.size() + usingConnectionCount.get() + threadPool.getActiveCount());
        // 已创建出来的的对象总数 > 配置的最小连接数 && 可用剩余超过配置spared
        int overMinNum = availableConnectionQueue.size() + usingConnectionCount.get() - poolConfig.getMinConnectionCount();
        int dif = poolConfig.getMinConnectionCount() - usingConnectionCount.get() > 0 ? poolConfig.getMinConnectionCount() - usingConnectionCount.get() : 0;
        int evictNum = availableConnectionQueue.size() - dif - sparedCount;
        log.info("overMinNum:{}, evictNum{}", overMinNum, evictNum);
        if (overMinNum > 0 && evictNum > 0) {
            Connection evictConn;
            for (int i = 0; i < evictNum; i++) {
                evictConn = availableConnectionQueue.iterator().next();
                // 多余的连接如果超期则移除
                if (System.currentTimeMillis() - evictConn.bornTime > poolConfig.getConnectionLifetime()) {
                    // TODO 要使用不阻塞的删除
                    if (availableConnectionQueue.poll() == null) {
                        log.error("poll null~~~~");
                    }
                    log.info("清除1空余连接！！！！");
                }
            }
        }
    }

    /**
     * 并发调用
     * 要保持的可用连接不够时增加连接数
     */
    private static synchronized void incrementConnection() {

        int maxUnexpiredNum = maxCount - threadPool.getActiveCount() - availableConnectionQueue.size() - usingConnectionCount.get();
        int sparedUnexpiredNum = sparedCount - availableConnectionQueue.size() - threadPool.getActiveCount();
        log.info("maxUnexpiredNum:{}， sparedUnexpiredNum：{}---vailableConnectionQueue.size():{}, threadPool.getActiveCount():{}, usingConnectionCount.get():{}", maxUnexpiredNum, sparedUnexpiredNum, availableConnectionQueue.size(),threadPool.getActiveCount(), usingConnectionCount.get());
        sparedUnexpiredNum = sparedUnexpiredNum <= maxUnexpiredNum ? sparedUnexpiredNum : maxUnexpiredNum;
        if (maxUnexpiredNum > 0 && sparedUnexpiredNum > 0) {
            log.info("要补充连接{}个对象: availableConnectionQueue.size():{},   threadPool.getActiveCount():{}", sparedUnexpiredNum, availableConnectionQueue.size(), threadPool.getActiveCount());
            for (int i = 0; i < sparedUnexpiredNum; i++) {
                threadPool.execute(() -> {
                    Connection conn = new Connection();
                    if (maxCount - threadPool.getActiveCount() - availableConnectionQueue.size() - usingConnectionCount.get() > 0) {
                        availableConnectionQueue.offer(conn);
                    }
                });
            }
        }
    }

    /**
     * 获取一个连接
     *
     * @return
     */
    public static Connection getConnection() throws InterruptedException {
        Connection conn;
        incrementConnection();
        conn = availableConnectionQueue.poll(poolConfig.getAcquireConnectionTimeout(), TimeUnit.MILLISECONDS);
        if (conn != null) {
            usingConnectionCount.incrementAndGet();
        } else {
            System.out.println("连接池中无可用连接！");
        }
        return conn;
    }

    /**
     * 并发调用.
     * 关闭
     */
    public static void close(Connection conn) {
        conn.bornTime = System.currentTimeMillis();
        PoolFactory.availableConnectionQueue.offer(conn);
        PoolFactory.usingConnectionCount.decrementAndGet();
    }

}
