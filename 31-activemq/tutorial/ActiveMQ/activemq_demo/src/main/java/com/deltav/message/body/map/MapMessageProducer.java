package com.deltav.message.body.map;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * MapMessage Producer 测试
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/3/6 21:34
 */
public class MapMessageProducer {
    private static final String ACTIVE_MQ_URL = "tcp://192.168.2.200:61616";
    private static final String QUEUE_NAME = "map_queue";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("Topic Message " + i);
            producer.send(textMessage);

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("key", "value" + i);
            producer.send(mapMessage);
        }

        producer.close();
        session.close();
        connection.close();
    }
}
