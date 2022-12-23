package com.lizuoyang.springboot.example;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @ClassName ProducerMain
 * @Description 消费者
 * @Author LiZuoYang
 * @Date 2022/3/30 16:26
 **/
public class ConsumerMain {
    public static Consumer<String, String> createConsumer() {
        // 设置consumer的属性
        Properties properties = new Properties();
        // 设置broker的地址
        properties.put("bootstrap.servers", "81.71.68.248:9092");
        // 设置消费者分组名称
        properties.put("group.id", "demo-consumer-group");
        /**
         * 设置消费者分组最初消费进度为earliest
         * auto.offset.reset值含义解释:
         * earliest 当分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费。
         * latest 当分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据。
         * none 当该topic下所有分区中存在未提交的offset时，抛出异常。
         */
        properties.put("auto.offset.reset", "earliest");
        // 是否自动提交消费进度
        properties.put("enable.auto.commit", true);
        // 自动提交消费进度的频率
        properties.put("auto.commit.interval.ms", "1000");
        // 消息的key反序列化的方式
        properties.put("key.deserializer", StringDeserializer.class.getName());
        // 设置value反序列化方式
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return new KafkaConsumer<>(properties);
    }
    public static void main(String[] args) {
        // 创建consumer
        Consumer<String, String> consumer = createConsumer();
        // 订阅消息
        consumer.subscribe(Collections.singleton("TestTopic"));
        // 拉取消息
        while (true) {
            // 拉取消息，如果拉取不到消息，阻塞等待10秒，或者等待拉取到消息
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
            // 遍历处理消息
            records.forEach(new java.util.function.Consumer<ConsumerRecord<String, String>>() {
                @Override
                public void accept(ConsumerRecord<String, String> record) {
                    System.out.println(record.key() + "\t" + record.value());
                }
            });
        }

    }
}
