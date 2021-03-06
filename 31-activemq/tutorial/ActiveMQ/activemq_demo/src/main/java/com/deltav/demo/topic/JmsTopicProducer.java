package com.deltav.demo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author DeltaV235
 * @version 1.0
 * @date 2021/2/28 17:58
 */
public class JmsTopicProducer {
    private static final String ACTIVE_MQ_URL = "tcp://192.168.2.200:61616";
    private static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);


        MessageProducer producer = session.createProducer(topic);

        for (int i = 0; i < 6; i++) {
            TextMessage textMessage = session.createTextMessage("Topic Message " + i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();
    }
}
