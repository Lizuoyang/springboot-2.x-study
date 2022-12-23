package com.lizuoyang.springboot;

import com.lizuoyang.springboot.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName Demo04ProducerTest
 * @Description 消费重试测试类
 * @Author LiZuoYang
 * @Date 2022/4/1 15:05
 **/
@Slf4j
@SpringBootTest
public class Demo04ProducerTest {

    @Autowired
    private Demo04Producer producer;


    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
