package com.lizuoyang.springboot.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @ClassName ProducerMain
 * @Description 生产者
 * @Author LiZuoYang
 * @Date 2022/3/30 16:26
 **/
public class ProducerMain {

    public static Producer<String, String> createProducer() {
        // 设置Producer的属性
        Properties properties = new Properties();
        // 设置broker地址
        properties.put("bootstrap.servers", "81.71.68.248:9092");
        // 0-不应答 1-leader 应答 all-所有leader和follower应答
        properties.put("ack", "1");
        // 发送失败时，重试次数
        properties.put("retries", 3);
        // 消息的key序列化方式
        properties.put("key.serializer", StringSerializer.class.getName());
        //消息的value序列化方式
        properties.put("value.serializer", StringSerializer.class.getName());

        // 创建kafka对象
        return new KafkaProducer<>(properties);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建kafka Producer对象
        Producer<String, String> producer = createProducer();

        // 创建消息，传入三个参数，Topic，key，message
        ProducerRecord<String, String> message = new ProducerRecord<>("TestTopic", "test_key", "Hello kafka");

        // 同步发送消息包
        Future<RecordMetadata> future = producer.send(message);
        RecordMetadata result = future.get();
        System.out.println("message sent to " + result.topic() + ", partition " + result.partition() + ", offset " + result.offset());
    }
}
