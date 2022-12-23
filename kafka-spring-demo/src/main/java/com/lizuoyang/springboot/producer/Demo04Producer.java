package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo04Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName Demo02Producer
 * @Description 消费重试producer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:49
 **/
@Component
public class Demo04Producer {
    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    /**
     * 同步发送
     *
     * @param id id
     * @return {@link SendResult}
     */
    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        // 创建消息对象
        Demo04Message message = new Demo04Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo04Message.TOPIC, message).get();
    }
}
