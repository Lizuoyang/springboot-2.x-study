package com.lizuoyang.springboot.producer;

import com.lizuoyang.springboot.message.Demo01Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName Demo01Producer
 * @Description Demo01Producer
 * @Author LiZuoYang
 * @Date 2022/4/1 10:07
 **/
@Component
public class Demo01Producer {

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
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC, message).get();
    }

    /**
     * 异步发送
     *
     * @param id id
     * @return {@link ListenableFuture}<{@link SendResult}>
     */
    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        // 创建消息对象
        Demo01Message message = new Demo01Message();
        message.setId(id);
        return kafkaTemplate.send(Demo01Message.TOPIC, message);
    }
}
