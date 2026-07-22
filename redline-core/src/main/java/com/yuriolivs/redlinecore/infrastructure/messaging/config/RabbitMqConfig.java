package com.yuriolivs.redlinecore.infrastructure.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE =
            "redline.exchange";
    public static final String ADS_SCRAPED_QUEUE =
            "redline.ads-scraped.queue";
    public static final String AD_UPDATED_QUEUE =
            "redline.ad-updated.queue";
    public static final String ALERT_QUEUE =
            "redline.alert.queue";
    public static final String ADS_SCRAPED_ROUTING_KEY =
            "ads-scraped";
    public static final String AD_UPDATED_ROUTING_KEY =
            "ad-updated";
    public static final String ALERT_ROUTING_KEY =
            "alert";

    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
        ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());

        return factory;
    }

    @Bean
    public Queue adsScrapedQueue() {
        return new Queue(ADS_SCRAPED_QUEUE);
    }

    @Bean
    public Queue adUpdatedQueue() {
        return new Queue(AD_UPDATED_QUEUE);
    }

    @Bean
    public Queue alertQueue() {
        return new Queue(ALERT_QUEUE);
    }

    @Bean
    public Binding adsScrapedBinding() {
        return BindingBuilder
                .bind(adsScrapedQueue())
                .to(exchange())
                .with(ADS_SCRAPED_ROUTING_KEY);
    }

    @Bean
    public Binding adUpdatedBinding() {
        return BindingBuilder
                .bind(adUpdatedQueue())
                .to(exchange())
                .with(AD_UPDATED_ROUTING_KEY);
    }

    @Bean
    public Binding alertBinding() {
        return BindingBuilder
                .bind(alertQueue())
                .to(exchange())
                .with(ALERT_ROUTING_KEY);
    }
}
