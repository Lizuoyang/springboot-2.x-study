package com.lizuoyang.springboot;

import com.lizuoyang.springboot.producer.Demo03Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Demo03ProducerTest
 * @Description 生产者测试用例02
 * @Author LiZuoYang
 * @Date 2022/3/28 16:45
 **/
@SpringBootTest
@Slf4j
public class Demo03ProducerTest {

    @Autowired
    private Demo03Producer producer;

    @Test
    public void testSyncSendDay() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id, 3); // 延迟级别 3 ，即 10 秒后消费
        log.info("[testSyncSendDelay][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
