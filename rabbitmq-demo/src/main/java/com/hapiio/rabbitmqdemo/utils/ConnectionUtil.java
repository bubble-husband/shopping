package com.hapiio.rabbitmqdemo.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {
    /**
     * 建立与RabbitMQ的连接
     * @return
     * @throws Exception
     */

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.126.22");
        //端口
        factory.setPort(5672);
        //设置账户信息，用户名，密码，vhost
        factory.setVirtualHost("hapiio2020");
        factory.setUsername("user");
        factory.setPassword("root");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;

    }
}
