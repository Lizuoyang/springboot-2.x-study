package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo03Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo03Producer
 * @Description producer 03
 * @Author LiZuoYang
 * @Date 2022/3/28 16:35
 **/
@Component
public class Demo03Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送
     *
     * @param id         id
     * @param delayLevel 延迟级别
     * 1	1s	7	3m	13	9m
     * 2	5s	8	4m	14	10m
     * 3	10s	9	5m	15	20m
     * 4	30s	10	6m	16	30m
     * 5	1m	11	7m	17	1h
     * 6	2m	12	8m	18	2h
     * @return {@link SendResult}
     */
    public SendResult syncSend(Integer id, int delayLevel) {
        Demo03Message demo03Message = new Demo03Message();
        demo03Message.setId(id);
        Message<Demo03Message> message = MessageBuilder.withPayload(demo03Message).build();
        SendResult sendResult = rocketMQTemplate.syncSend(Demo03Message.TOPIC, message,30 * 1000, delayLevel);
        return sendResult;
    }

    public void asyncSend(Integer id, int delayLevel, SendCallback callback) {
        Demo03Message demo03Message = new Demo03Message();
        demo03Message.setId(id);
        Message<Demo03Message> message = MessageBuilder.withPayload(demo03Message).build();
        rocketMQTemplate.asyncSend(Demo03Message.TOPIC, message, callback,30 * 1000, delayLevel);
    }
}
