package com.deltav;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ActiveMQ 测试 Demo
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/28 11:15
 */
@Slf4j
public class ActiveMqDemo {
    private static final String ACTIVE_MQ_URL = "tcp://192.168.2.200:61616";
    private static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        // 1.创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_URL);

        // 2.获取连接并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        // 3.创建会话 Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4.创建 Destination
        Queue queue = session.createQueue(QUEUE_NAME);

        // 5.创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        // 6.通过 producer 将 3 条消息发送到 MQ 的队列中
        for (int i = 0; i < 3; i++) {
            // 7.创建消息
            TextMessage textMessage = session.createTextMessage("TEXT_" + i);
            // 8.通过 producer 发送给 MQ
            producer.send(textMessage);
        }

        // 9.关闭资源
        producer.close();
        session.close();
        connection.close();

        log.info("Text Message has sent to MQ!");
    }
}
