package com.lizuoyang.springboot.consumer;

import com.lizuoyang.springboot.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo02Consumer
 * @Description Demo02Consumer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:14
 **/
@Component
@Slf4j
public class Demo02Consumer {
    @KafkaListener(
            topics = Demo02Message.TOPIC,
            groupId = "kafka-demo02-consumer-group-" + Demo02Message.TOPIC
    )
    public void onMessage(Demo02Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
