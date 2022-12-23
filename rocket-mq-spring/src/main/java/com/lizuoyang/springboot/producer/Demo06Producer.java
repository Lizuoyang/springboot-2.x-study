package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo01Message;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo01Producer
 * @Description producer 06
 * @Author LiZuoYang
 * @Date 2022/3/28 14:28
 **/
@Component
public class Demo06Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送
     *
     * @param id id
     * @return {@link SendResult}
     */
    public SendResult syncSendOrderly(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(Demo01Message.TOPIC, message, String.valueOf(id));
        return sendResult;
    }

    /**
     * 异步发送
     *
     * @param id id
     */
    public void asyncSendOrderly(Integer id, SendCallback callback) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.asyncSendOrderly(Demo01Message.TOPIC, message, String.valueOf(id),callback);
    }

    /**
     * 单向发送
     *
     * @param id id
     */
    public void onewaySendOrderly(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rocketMQTemplate.sendOneWayOrderly(Demo01Message.TOPIC, message, String.valueOf(id));
    }
}
