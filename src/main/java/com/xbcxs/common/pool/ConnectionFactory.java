package com.xbcxs.common.pool;

/**
 * @author xiaosh
 * @date 2019/12/23
 */
public class ConnectionFactory {

    private static Connection connection;

    public static void register(Connection c) {
        connection = c;
    }

    public static Connection createConnection() {
        return connection.getObject();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InterruptedException {
        Class.forName("com.xbcxs.common.pool.AConn");
        Connection conn1 = (Connection) ConnectionFactory.createConnection();
        System.out.println("conn1:" + conn1.bornTime);
        Thread.sleep(1000);

        Connection conn2 = (Connection) ConnectionFactory.createConnection();
        System.out.println("conn2:" + conn2.bornTime);
    }

}
