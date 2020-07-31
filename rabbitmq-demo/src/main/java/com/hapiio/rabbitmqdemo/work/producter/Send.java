package com.hapiio.rabbitmqdemo.work.producter;

import com.hapiio.rabbitmqdemo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception{

        // 获取到连接以及MQ通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道,这是完成大部分API的地方
        Channel channel = connection.createChannel();

        // 声明（创建）队列,必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        // 声明一个队列是幂等的 - 只有当它不存在时才会被创建
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 循环发布任务
        for (int i = 0; i < 50; i++) {
            // 消息内容
            String message = "task .. " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 2);
        }

        //关闭通道和连接
        channel.close();
        connection.close();


    }


}
