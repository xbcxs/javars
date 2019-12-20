package com.xbcxs.common.pool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class Consumer {

    static AtomicInteger exccuteCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        test2(10000, 60 * 1000);

        Thread.sleep(3600 * 1000);
    }

    private static void test1() throws InterruptedException {
        int no = exccuteCount.incrementAndGet();
        long startTime = System.currentTimeMillis();
//        System.out.println(no + "，执行开始！");
        Connection conn = PoolFactory.getConnection();
        Thread.sleep(100);
//        System.out.println(no + "，得到连接！");
        if (conn != null) {
            PoolFactory.close(conn);
        }
//        System.out.println(no + "，关闭连接！" + "耗时：" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 多少人并发多久
     * @param number 人数
     * @param time 并发时间
     */
    public static void test2(int number, long time) {
        long endTime = System.currentTimeMillis() + time;
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(500);

        for (int i = 0; i < number; i++) {
            System.out.println("i--->" + i);
            executor.scheduleWithFixedDelay(() -> {
                try {
                    test1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (System.currentTimeMillis() > endTime) {
                    executor.shutdown();
                }
            }, 0, 1, TimeUnit.MILLISECONDS);
        }
    }
}
