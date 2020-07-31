package com.hapiio.rabbitmqdemo.fanout.producter;

import com.hapiio.rabbitmqdemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws Exception{

        // 获取到连接以及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道,这是完成大部分API的地方
        Channel channel = connection.createChannel();

        // 声明声明exchange，指定类型为fanout
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        // 循环发布任务
        for (int i = 0; i < 10; i++) {
            // 消息内容
            String message = "task .. " + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println(" [生产者] Sent '" + message + "'");

            Thread.sleep(i * 2);
        }

        //关闭通道和连接
        channel.close();
        connection.close();


    }


}
