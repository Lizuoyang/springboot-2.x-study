package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo02Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;

/**
 * @ClassName Demo02Producer
 * @Description 批量发送消息producer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:49
 **/
@Component
public class Demo02Producer {
    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    /**
     * 异步发送
     *
     * @param id id
     * @return {@link ListenableFuture}<{@link SendResult}>
     */
    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        // 创建消息对象
        Demo02Message message = new Demo02Message();
        message.setId(id);
        return kafkaTemplate.send(Demo02Message.TOPIC, message);
    }
}
