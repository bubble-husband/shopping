package com.hapiio.rabbitmqdemo.topic.consumer;

import com.hapiio.rabbitmqdemo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv2 {

    private final static String EXCHANGE_NAME = "topic_exchange";

    private final static String QUEUE_NAME = "queue_2";

    public static void main(String[] args) throws Exception{
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        // 绑定队列到交换机，同时指定需要订阅的routing key。
        // 订阅 insert、update、delete
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.*");


        // 设置每个消费者同时只能处理一条消息。
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("从队列"+QUEUE_NAME+"接受到消费者2：" + message);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        };

        // 监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);


    }
}
