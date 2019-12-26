package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/23
 */
public class PoolTest2 {

    static LinkedBlockingQueueA<Object> lbq = new LinkedBlockingQueueA(100);

    public static void main(String[] args) throws InterruptedException {
        lbq.offer(new Object());
        lbq.offer(new Object());
        lbq.offer(new Object());
        Thread.sleep(100);
        System.out.println("size1:" + lbq.size());
        new Thread(() -> {
            try {
                lbq.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(100);
        lbq.poll();
        System.out.println("size:" + lbq.size());
    }

}
