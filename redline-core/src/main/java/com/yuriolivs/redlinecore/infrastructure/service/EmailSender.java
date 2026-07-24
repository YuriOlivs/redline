package com.yuriolivs.redlinecore.infrastructure.service;

import com.yuriolivs.redlinecore.domain.service.IEmailSender;
import com.yuriolivs.redlinecore.domain.service.Email;
import com.yuriolivs.redlinecore.infrastructure.properties.EmailFromProperties;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailSender implements IEmailSender {
    private final JavaMailSender mailSender;
    private final EmailFromProperties fromProperties;

    @Override
    public void send(Email email) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(
                mimeMessage,
                true,
                "UTF-8"
        );

        mimeHelper.setFrom(new InternetAddress(
            fromProperties.getAddress(),
            fromProperties.getName()
        ));

        mimeHelper.setTo(email.getRecipient());
        mimeHelper.setSubject(email.getSubject());
        mimeHelper.setText(email.getBody(), true);

        mailSender.send(mimeMessage);
    }
}
