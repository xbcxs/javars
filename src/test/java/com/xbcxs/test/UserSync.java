package com.xbcxs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class UserSync {

    private static Logger log = LoggerFactory.getLogger(UserSync.class);

    private static volatile boolean isLoadUser = false;

    /**
     * 系统启动后调用
     */
    public static void initLoadUser() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ScheduledFuture sf = executor.scheduleWithFixedDelay(() -> {
            if(!isLoadUser){
                try {
                    // TODO something




                    isLoadUser = true;
                    log.info("完成!");
                } catch (Exception e) {
                    log.warn("请求失败...");
                }
            } else {
                executor.shutdown();
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

}
