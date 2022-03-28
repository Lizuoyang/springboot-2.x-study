package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo07Message;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo07Producer
 * @Description producer 07
 * @Author LiZuoYang
 * @Date 2022/3/28 18:34
 **/
@Component
public class Demo07Producer {
    public static final String TX_PRODUCER_GROUP = "demo07-producer-group";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public TransactionSendResult sendMessageInTransaction(Integer id) {
        Demo07Message message = new Demo07Message();
        message.setId(id);
        Message<Demo07Message> message1 = MessageBuilder.withPayload(message).build();
        return rocketMQTemplate.sendMessageInTransaction(TX_PRODUCER_GROUP, Demo07Message.TOPIC, message1, id);
    }
}
