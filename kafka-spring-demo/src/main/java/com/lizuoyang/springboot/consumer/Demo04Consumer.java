package com.lizuoyang.springboot.consumer;

import com.lizuoyang.springboot.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo02Consumer
 * @Description Demo04Consumer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:14
 **/
@Component
@Slf4j
public class Demo04Consumer {
    @KafkaListener(
            topics = Demo04Message.TOPIC,
            groupId = "kafka-demo04-consumer-group-" + Demo04Message.TOPIC
    )
    public void onMessage(Demo04Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我是故意抛出的一个异常");
    }
}
