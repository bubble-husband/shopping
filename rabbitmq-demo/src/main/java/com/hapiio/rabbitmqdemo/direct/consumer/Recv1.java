package com.hapiio.rabbitmqdemo.direct.consumer;

import com.hapiio.rabbitmqdemo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv1 {

    private final static String EXCHANGE_NAME = "direct_exchange";

    private final static String QUEUE_NAME = "queue_1";

    public static void main(String[] args) throws Exception{
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        // 绑定队列到交换机
        // 绑定队列到交换机，同时指定需要订阅的routing key。
        // 假设此处需要update和delete消息
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("从队列"+QUEUE_NAME+"接受到消费者1：" + message);

            }
        };

        // 监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);


    }
}
