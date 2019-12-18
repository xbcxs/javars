package com.xbcxs.common.pool;

/**
 * 懒加载单例
 *
 * @author xiaosh
 * @date 2019/12/12
 */
public class PoolConfig {

    /** 最小连接数 */
    private int minConnectionCount;

    /** 最大连接数 */
    private int maxConnectionCount;

    /** 连接池中可用连接数 */
    private int sparedConnectionCount;

    /** 连接对象存活时间 */
    private int connectionLifetime;

    /** 连接对象间隔检查时间（用于补足/清除超期sparedConnection） */
    private int connectionCheckInterval;

    /** 获取连接对象超时时间 */
    private int acquireConnectionTimeout;

    private static PoolConfig poolConfig;

    private PoolConfig() {
        // 读取配置文件

        // 初始化值
        this.minConnectionCount = 5;
        this.maxConnectionCount = 30;
        this.sparedConnectionCount = 5;
        this.connectionLifetime = 300 * 1000;
        this.connectionCheckInterval = 3 * 1000;
        this.acquireConnectionTimeout = 300 * 1000;

        // 对各参数进行边界截取
    }

    public static PoolConfig getConfig() {
        if (poolConfig == null) {
            synchronized (PoolConfig.class) {
                if (poolConfig == null) {
                    poolConfig = new PoolConfig();
                }
            }
        }
        return poolConfig;
    }

    public int getMinConnectionCount() {
        return minConnectionCount;
    }

    public void setMinConnectionCount(int minConnectionCount) {
        this.minConnectionCount = minConnectionCount;
    }

    public int getMaxConnectionCount() {
        return maxConnectionCount;
    }

    public void setMaxConnectionCount(int maxConnectionCount) {
        this.maxConnectionCount = maxConnectionCount;
    }

    public int getSparedConnectionCount() {
        return sparedConnectionCount;
    }

    public void setSparedConnectionCount(int sparedConnectionCount) {
        this.sparedConnectionCount = sparedConnectionCount;
    }

    public int getConnectionLifetime() {
        return connectionLifetime;
    }

    public void setConnectionLifetime(int connectionLifetime) {
        this.connectionLifetime = connectionLifetime;
    }

    public int getConnectionCheckInterval() {
        return connectionCheckInterval;
    }

    public void setConnectionCheckInterval(int connectionCheckInterval) {
        this.connectionCheckInterval = connectionCheckInterval;
    }

    public int getAcquireConnectionTimeout() {
        return acquireConnectionTimeout;
    }

    public void setAcquireConnectionTimeout(int acquireConnectionTimeout) {
        this.acquireConnectionTimeout = acquireConnectionTimeout;
    }
}
