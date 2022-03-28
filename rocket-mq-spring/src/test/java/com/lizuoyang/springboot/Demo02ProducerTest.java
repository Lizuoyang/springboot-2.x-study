package com.lizuoyang.springboot;

import com.lizuoyang.springboot.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Demo02ProducerTest
 * @Description 生产者测试用例02
 * @Author LiZuoYang
 * @Date 2022/3/28 16:15
 **/
@SpringBootTest
@Slf4j
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSendBatch() throws InterruptedException {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        SendResult sendResult = producer.sendBatch(ids);
        log.info("[testSendBatch][发送编号：[{}] 发送结果：[{}]]", ids, sendResult);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
