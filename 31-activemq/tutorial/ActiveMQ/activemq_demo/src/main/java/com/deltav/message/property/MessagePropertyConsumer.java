package com.deltav.message.property;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 消息数据 consumer 测试类
 *
 * @author DeltaV235
 * @version 1.0
 * @date 2021/3/7 0:15
 */
public class MessagePropertyConsumer {
    private static final String ACTIVE_MQ_URL = "tcp://192.168.2.200:61616";
    private static final String QUEUE_NAME = "property_queue";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        // 监听器方法
        consumer.setMessageListener(message -> {
            if (null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("TextMessage -> " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (null != message && message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                try {
                    System.out.println("MapMessage -> " + mapMessage.getString("key"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if (null != message && message instanceof StreamMessage) {
                StreamMessage streamMessage = (StreamMessage) message;
                try {
                    byte[] bytes = new byte[50];
                    streamMessage.readBytes(bytes);
                    System.out.println("StreamMessage -> " + new String(bytes));
                    System.out.println("Message Property -> " + streamMessage.getStringProperty("property"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
