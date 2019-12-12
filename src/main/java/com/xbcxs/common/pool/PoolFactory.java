package com.xbcxs.common.pool;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 池初始化时机：
 *
 * @author xiaosh
 * @date 2019/12/12
 */
public class PoolFactory {

    /**
     * 线程安全无界非阻塞队列
     */
    public static ConcurrentLinkedQueue<Connection> leisureQueue = new ConcurrentLinkedQueue();

    public static List<Connection> busyList = new LinkedList<>();

    private static PoolConfig config = PoolConfig.getConfig();

    static {
        while (leisureQueue.size() + busyList.size() < config.getMinConnection()) {
            Connection conn = new Connection();
            leisureQueue.add(conn);
        }
        new Thread(() -> cycleCheck()).start();
    }

    private static void cycleCheck() {
        // TODO 改进成异步非阻塞轮训调度
        while (true) {
            // 保持最小连接对象个数
            while (leisureQueue.size() + busyList.size() < config.getMinConnection()) {
                Connection conn = new Connection();
                leisureQueue.add(conn);
            }
            // 清除超时空闲连接对象
            int residueMinSum = config.getMinConnection() - busyList.size();
            residueMinSum = residueMinSum > 0 ? residueMinSum : 0;
            int evictSum = leisureQueue.size() - residueMinSum;
            if (evictSum > 0 && !leisureQueue.isEmpty()) {
                int i = 0;
                Iterator<Connection> iterator = leisureQueue.iterator();
                Connection evicConn;
                while (iterator.hasNext() && i < evictSum) {
                    evicConn = iterator.next();
                    if (System.currentTimeMillis() - evicConn.bornTimeValue > config.getExpireTime()) {
                        leisureQueue.remove(evicConn);
                    }
                    i++;
                }
            }
            // 间隔
            try {
                System.out.println("cycleCheck sleep:" + 5 * 1000);
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void queuePrint() {
        Iterator<Connection> iterator = PoolFactory.leisureQueue.iterator();
        System.out.println("--------------start");
        while (iterator.hasNext()) {
            System.out.println("bornTime:" + iterator.next().bornTimeValue);
        }
        System.out.println("--------------end");
    }


    public static Connection getConnection() {
        Connection conn = leisureQueue.poll();
        busyList.add(conn);
        return conn;
    }

}
