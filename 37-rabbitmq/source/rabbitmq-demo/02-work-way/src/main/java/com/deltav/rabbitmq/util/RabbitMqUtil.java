package com.deltav.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置主机地址
        connectionFactory.setHost("192.168.0.31");

        // 设置连接端口号：默认为 5672
        connectionFactory.setPort(5672);

        // 虚拟主机名称：默认为 /
        connectionFactory.setVirtualHost("/");

        // 设置连接用户名；默认为guest
        connectionFactory.setUsername("guest");

        // 设置连接密码；默认为guest
        connectionFactory.setPassword("123456");

        // 创建连接
        return connectionFactory.newConnection();
    }
}
