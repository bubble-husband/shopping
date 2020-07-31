package com.hapiio.rabbitmqdemo.direct.producter;

import com.hapiio.rabbitmqdemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "direct_exchange";

    public static void main(String[] args) throws Exception{

        // 获取到连接以及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道,这是完成大部分API的地方
        Channel channel = connection.createChannel();

        // 声明声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 循环发布任务
        for (int i = 0; i < 10; i++) {
            // 消息内容
            String insert_message = "商品新增：，id=" + i;
            // 发送消息，并且指定routing key 为：insert ,代表新增商品
            channel.basicPublish(EXCHANGE_NAME, "insert", null, insert_message.getBytes());
            System.out.println(" [商品服务] Sent '" + insert_message + "'");
            // 消息内容
            String update_message = "商品更新：，id=" + i;
            // 发送消息，并且指定routing key 为：insert ,代表新增商品
            channel.basicPublish(EXCHANGE_NAME, "update", null, update_message.getBytes());
            System.out.println(" [商品服务] Sent '" + update_message + "'");
            // 消息内容
            String delete_message = "商品删除：，id=" + i;
            // 发送消息，并且指定routing key 为：insert ,代表新增商品
            channel.basicPublish(EXCHANGE_NAME, "delete", null, delete_message.getBytes());
            System.out.println(" [商品服务] Sent '" + delete_message + "'");

            Thread.sleep(i * 2);
        }

        //关闭通道和连接
        channel.close();
        connection.close();


    }


}
