package com.lizuoyang.springboot.consumer;

import com.lizuoyang.springboot.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.DefaultMenuLayout;

/**
 * @ClassName Demo01Consumer
 * @Description Demo01Consumer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:14
 **/
@Component
@Slf4j
public class Demo01Consumer {
    @KafkaListener(
            topics = Demo01Message.TOPIC,
            groupId = "kafka-demo01-consumer-group-" + Demo01Message.TOPIC
    )
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
