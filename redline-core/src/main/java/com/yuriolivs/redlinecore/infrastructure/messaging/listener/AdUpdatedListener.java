package com.yuriolivs.redlinecore.infrastructure.messaging.listener;

import com.yuriolivs.redlinecore.application.advertisement.usecase.GetUsersThatSavedByAdvertisementUseCase;
import com.yuriolivs.redlinecore.application.alert.usecase.SendAdUpdatedAlertUseCase;
import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.messaging.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdUpdatedListener {
    private final GetUsersThatSavedByAdvertisementUseCase getUsersUseCase;
    private final SendAdUpdatedAlertUseCase sendAlertUseCase;

    @RabbitListener(queues = RabbitMqConfig.AD_UPDATED_QUEUE)
    public void consume(AdUpdatedEvent event) {
        List<Alert> alertsToBeSent = getUsersUseCase.execute(event.getAd()
                .getUrl())
                .stream()
                .map(User::getId)
                .map(userId ->
                        new Alert(
                            event.getType(),
                            event.getAd().getId(),
                            userId,
                            event.getTriggeredAt()
                        )
                )
                .toList();

        alertsToBeSent.forEach(sendAlertUseCase::execute);
    }
}


