package com.lizuoyang.springboot.consumer;

import com.lizuoyang.springboot.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo01Consumer
 * @Description consumer 04
 * @Author LiZuoYang
 * @Date 2022/3/28 14:29
 **/
@Slf4j
@Component
@RocketMQMessageListener(
        topic = Demo04Message.TOPIC,
        consumerGroup = "demo04-consumer-group-" + Demo04Message.TOPIC
)
public class Demo04Consumer implements RocketMQListener<Demo04Message> {
    @Override
    public void onMessage(Demo04Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我就是故意抛出一个异常");
    }
}
