package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo02Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Demo02Message
 * @Description producer 02
 * @Author LiZuoYang
 * @Date 2022/3/28 15:58
 **/
@Component
public class Demo02Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public SendResult sendBatch(List<Integer> ids) {
        List<Message> messages = new ArrayList<>(ids.size());
        ids.forEach(id -> {
            Demo02Message message = new Demo02Message();
            message.setId(id);
            messages.add(MessageBuilder.withPayload(message).build());
        });

        return rocketMQTemplate.syncSend(Demo02Message.TOPIC, messages, 30 * 1000L);
    }
}
