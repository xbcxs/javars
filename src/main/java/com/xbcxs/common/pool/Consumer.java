package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/12
 */
public class Consumer {

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() {
        PoolFactory.queuePrint();
        // 得到一个链接
        Connection conn = PoolFactory.getConnection();

        PoolFactory.queuePrint();
        // 处理业务


        // 关闭链接
        if (conn != null) {
            conn.close();
        }
        PoolFactory.queuePrint();
    }
}
