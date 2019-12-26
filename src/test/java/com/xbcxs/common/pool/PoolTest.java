package com.xbcxs.common.pool;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaosh
 * @date 2019/12/23
 */
public class PoolTest {

    private final AtomicInteger count = new AtomicInteger(1);
    /** Lock held by take, poll, etc */
    private final ReentrantLock aLock = new ReentrantLock();
    /** Wait queue for waiting takes */
    private final Condition aCondition = aLock.newCondition();
    /** Lock held by put, offer, etc */
    private final ReentrantLock bLock = new ReentrantLock();
    /** Wait queue for waiting puts */
    private final Condition bCondition = bLock.newCondition();

    public static  void main(String[] args) throws InterruptedException {
        PoolTest pt = new PoolTest ();
        new Thread(() -> {
            pt.test1();
        }).start();
        Thread.sleep(100);
        pt.test2();
    }

    public void test1(){
        final ReentrantLock aLock = this.aLock;
        final AtomicInteger count = this.count;
        try {
            aLock.lockInterruptibly();
            count.incrementAndGet();
            System.out.println("come test1 incrementAndGet finished,count:" + count.get());
            Thread.sleep(1 * 1000);
            System.out.println("come test1 result:" + count.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            aLock.unlock();
        }
    }

    public void test2(){
        final ReentrantLock bLock = this.bLock;
        final AtomicInteger count = this.count;
        try {
            bLock.lockInterruptibly();
            count.decrementAndGet();
            System.out.println("come test2,count:" + count.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bLock.unlock();
        }
    }

}
