package com.lizuoyang.springboot.example;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @ClassName Producer
 * @Description 生产者
 * @Author LiZuoYang
 * @Date 2022/3/25 16:16
 **/
public class Producer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        // <1.1> 创建 DefaultMQProducer 对象
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("LZY_PG_01");
        // <1.2> 设置 RocketMQ Namesrv 地址
        defaultMQProducer.setNamesrvAddr("81.71.68.248:9876");
        // <1.3> 启动 producer 生产者
        defaultMQProducer.start();

        for (int i = 0; i < 1000; i++) {
            try {
                // <2.1> 创建 Message 消息
                Message msg = new Message("TopicTest" /* Topic */,
                        "TagA" /* Tag */,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
                );

                // <2.2> 同步发送消息
                SendResult sendResult = defaultMQProducer.send(msg);

                // <2.3> 打印发送结果
                System.out.printf("%s%n", sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
    }
}
