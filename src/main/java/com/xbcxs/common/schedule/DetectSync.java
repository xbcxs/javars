package com.xbcxs.common.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 *
 * @author xiaosh
 * @date 2019/9/18
 */
public class DetectSync {

    private static Logger log = LoggerFactory.getLogger(DetectSync.class);

    private static volatile boolean isLoadUser = false;

    /**
     * 系统启动后调用
     */
    public static void init() {
        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture sf = executor.scheduleWithFixedDelay(() -> {
            if(!isLoadUser){
                try {
                    // TODO something

                    isLoadUser = true;
                    executor.shutdown();
                    log.info("完成!");
                } catch (Exception e) {
                    log.warn("请求失败...");
                }
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

}
