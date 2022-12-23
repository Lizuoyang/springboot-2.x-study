package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @ClassName TransactionListenerImpl
 * @Description rocketmq 事务消息实现类
 * @Author LiZuoYang
 * @Date 2022/3/28 18:41
 **/
@Slf4j
@RocketMQTransactionListener(txProducerGroup  = Demo07Producer.TX_PRODUCER_GROUP)
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    /**
     * 一般来说，有两种方式实现本地事务回查时，返回事务消息的状态。
     *
     * 第一种，通过 msg 消息，获得某个业务上的标识或者编号，然后去数据库中查询业务记录，从而判断该事务消息的状态是提交还是回滚。
     *
     * 第二种，记录 msg 的事务编号，与事务状态到数据库中。
     *
     * 第一步，在 #executeLocalTransaction(...) 方法中，先存储一条 id 为 msg 的事务编号，状态为 RocketMQLocalTransactionState.UNKNOWN 的记录。
     * 第二步，调用带有事务的业务 Service 的方法。在该 Service 方法中，在逻辑都执行成功的情况下，更新 id 为 msg 的事务编号，状态变更为 RocketMQLocalTransactionState.COMMIT 。这样，我们就可以伴随这个事务的提交，更新 id 为 msg 的事务编号的记录的状为 RocketMQLocalTransactionState.COMMIT ，美滋滋。。
     * 第三步，要以 try-catch 的方式，调用业务 Service 的方法。如此，如果发生异常，回滚事务的时候，可以在 catch 中，更新 id 为 msg 的事务编号的记录的状态为 RocketMQLocalTransactionState.ROLLBACK 。😭 极端情况下，可能更新失败，则打印 error 日志，告警知道，人工介入。
     * 如此三步之后，我们在 #executeLocalTransaction(...) 方法中，就可以通过查找数据库，id 为 msg 的事务编号的记录的状态，然后返回。
     * 相比来说，艿艿倾向第二种，实现更加简单通用，对于业务开发者，更加友好。和有几个朋友沟通了下，他们也是采用第二种。
     */

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        // ... local transaction process, return rollback, commit or unknown
        log.info("[executeLocalTransaction][执行本地事务，消息：{} arg：{}]", message, arg);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        // ... check transaction status and return rollback, commit or unknown
        log.info("[checkLocalTransaction][回查消息：{}]", message);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
