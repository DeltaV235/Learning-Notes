package com.deltav.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.amqp.CachingConnectionFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.ConnectionFactoryCustomizer;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionFactoryBeanConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMqConfig {

    @Bean
    CachingConnectionFactory connectionFactory(
            RabbitConnectionFactoryBeanConfigurer rabbitConnectionFactoryBeanConfigurer,
            CachingConnectionFactoryConfigurer rabbitCachingConnectionFactoryConfigurer,
            ObjectProvider<ConnectionFactoryCustomizer> connectionFactoryCustomizers) throws Exception {

        RabbitConnectionFactoryBean connectionFactoryBean = new RabbitConnectionFactoryBean();
        rabbitConnectionFactoryBeanConfigurer.configure(connectionFactoryBean);
        connectionFactoryBean.afterPropertiesSet();
        com.rabbitmq.client.ConnectionFactory connectionFactory = connectionFactoryBean.getObject();
        connectionFactoryCustomizers.orderedStream()
                .forEach((customizer) -> customizer.customize(connectionFactory));

        CachingConnectionFactory factory = new CachingConnectionFactory(connectionFactory);
        rabbitCachingConnectionFactoryConfigurer.configure(factory);

        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("correlationData: {}, ack: {}, cause: {}", correlationData, ack, cause);
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            log.info("returned: {}, replyCode: {}, replyText: {}, exchange: {}, routingKey: {}",
                    returned,
                    returned.getReplyCode(),
                    returned.getReplyText(),
                    returned.getExchange(),
                    returned.getRoutingKey());
        });

        rabbitTemplate.setMandatory(true);

        return rabbitTemplate;
    }

    @Bean
    public CachingConnectionFactory connectionFactoryTx() {
        CachingConnectionFactory factory = new CachingConnectionFactory("192.168.0.31");
        factory.setUsername("guest");
        factory.setPassword("123456");
        factory.setVirtualHost("/");
        return factory;
    }

    @Bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactoryTx) {
        return new RabbitTransactionManager(connectionFactoryTx);
    }

    @Bean
    public RabbitTemplate rabbitTemplateWithTx(CachingConnectionFactory connectionFactoryTx) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactoryTx);
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.addBeforePublishPostProcessors(message -> {
            log.info("message sent, {}", new String(message.getBody()));
            return message;
        });
        return rabbitTemplate;
    }
}
