package com.lizuoyang.springboot.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName Consumer
 * @Description 消费者
 * @Author LiZuoYang
 * @Date 2022/3/25 16:32
 **/
public class PushConsumer {
    public static void main(String[] args) throws MQClientException {
        // <1> 创建 DefaultMQPushConsumer 对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("LZY_CS_01");
        // <2> 设置 RocketMQ Namesrv 地址
        consumer.setNamesrvAddr("81.71.68.248:9876");
        // <3> 设置消费进度，从 Topic 最初位置开始
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // <4> 订阅 TopicTest 主题
        consumer.subscribe("TopicTest", "*");
        // <5> 添加消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 返回成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });

        /*
         *  Launch the consumer instance.
         */
        // <6> 启动 producer 消费者
        consumer.start();

        // 打印 Consumer 启动完成
        System.out.printf("Consumer Started.%n");
    }
}
