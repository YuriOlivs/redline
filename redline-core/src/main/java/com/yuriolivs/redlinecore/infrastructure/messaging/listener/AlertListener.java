package com.yuriolivs.redlinecore.infrastructure.messaging.listener;

import com.yuriolivs.redlinecore.application.advertisement.usecase.GetSpecificAdUseCase;
import com.yuriolivs.redlinecore.application.user.usecase.GetSpecificUserUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.PriceRecord;
import com.yuriolivs.redlinecore.domain.event.AdAlertTriggeredEvent;
import com.yuriolivs.redlinecore.domain.service.Email;
import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.messaging.config.RabbitMqConfig;
import com.yuriolivs.redlinecore.infrastructure.service.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertListener {
    private final EmailSender emailSender;
    private final GetSpecificAdUseCase getAdUseCase;
    private final GetSpecificUserUseCase getUserUseCase;

    @RabbitListener(queues = RabbitMqConfig.ALERT_QUEUE)
    public void consume(AdAlertTriggeredEvent event) throws Exception {
        Advertisement ad = getAdUseCase.execute(event.getAdvertisement());
        User user = getUserUseCase.execute(event.getTargetUser());

        List<PriceRecord> priceHistory = new ArrayList<>(ad.getPriceHistory());
        priceHistory.sort(Comparator.comparing(PriceRecord::getDate).reversed());

        Double previousPrice = priceHistory.get(1).getPrice();
        Double currentPrice = ad.getPrice();
        Integer currentScore = ad.getScore();

        String textBody = """
                The advertisement %s you saved had a change in it's price! \
                 Previous price: %.2f \
                 Current price: %.2f \
                 Score: %d""";

        Email email = new Email(
                user.getEmail(),
                event.getType().toString(),
                textBody.formatted(
                        ad.getTitle(),
                        previousPrice,
                        currentPrice,
                        currentScore
                )
        );

        emailSender.send(email);
        throw new UnsupportedOperationException("TODO: Implement the correct email template.");
    }
}
