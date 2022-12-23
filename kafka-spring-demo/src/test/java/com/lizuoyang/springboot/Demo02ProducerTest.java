package com.lizuoyang.springboot;

import com.lizuoyang.springboot.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Demo02ProducerTest
 * @Description 批量发送消息测试类
 * @Author LiZuoYang
 * @Date 2022/4/1 10:59
 **/
@Slf4j
@SpringBootTest
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testAsyncSend() throws InterruptedException {
        log.info("[testASyncSend][开始执行]");

        for (int i = 0; i < 3; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, throwable);
                }

                @Override
                public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
                    log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, objectObjectSendResult);
                }
            });

            // 故意每条消息之间，隔离 10 秒
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
