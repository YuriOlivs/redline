package com.yuriolivs.redlinecore.infrastructure.messaging.producer;

import com.yuriolivs.redlinecore.domain.event.AdAlertTriggeredEvent;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.event.DomainEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;
import com.yuriolivs.redlinecore.infrastructure.messaging.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher implements IEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DomainEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,
                defineRoutingKey(event),
                event
        );
    }

    private String defineRoutingKey(DomainEvent event) {
        if (event instanceof AdsScrapedEvent)
            return RabbitMqConfig.ADS_SCRAPED_ROUTING_KEY;
        if (event instanceof AdUpdatedEvent)
            return RabbitMqConfig.AD_UPDATED_ROUTING_KEY;
        if (event instanceof AdAlertTriggeredEvent)
            return RabbitMqConfig.ALERT_ROUTING_KEY;

        throw new IllegalArgumentException("No routing key found for "+ event.getClass());
    }
}
