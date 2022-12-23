package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo05Message;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName Demo01Producer
 * @Description producer 05
 * @Author LiZuoYang
 * @Date 2022/3/28 14:28
 **/
@Component
public class Demo05Producer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送
     *
     * @param id id
     * @return {@link SendResult}
     */
    public SendResult syncSend(Integer id) {
        Demo05Message message = new Demo05Message();
        message.setId(id);
        SendResult sendResult = rocketMQTemplate.syncSend(Demo05Message.TOPIC, message);
        return sendResult;
    }

}
