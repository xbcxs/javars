package com.xbcxs.common.pool;

/**
 * 懒加载单例
 *
 * @author xiaosh
 * @date 2019/12/12
 */
public class PoolConfig {

    private int minConnection;

    private int maxConnection;

    private int expireTime;

    private static PoolConfig pc = null;

    private PoolConfig() {
        // 读取配置文件

        // 初始化值
        this.minConnection = 2;
        this.maxConnection = 10;
        this.expireTime = 30 * 1000;
    }

    public static PoolConfig getConfig() {
        if (pc == null) {
            synchronized (PoolConfig.class) {
                if (pc == null) {
                    pc = new PoolConfig();
                }
            }
        }
        return pc;
    }

    public int getMinConnection() {
        return minConnection;
    }

    public void setMinConnection(int minConnection) {
        this.minConnection = minConnection;
    }

    public int getMaxConection() {
        return maxConnection;
    }

    public void setMaxConection(int maxConection) {
        this.maxConnection = maxConection;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }
}
