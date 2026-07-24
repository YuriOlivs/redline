package com.yuriolivs.redlinecore.domain.service;

import lombok.Getter;

@Getter
public class Email {
    private final String recipient;
    private final String subject;
    private final String body;

    private static final String EMAIL_REGEX = "((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])";

    public Email(String recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    private void validateRecipient(String recipient) {
        if (!recipient.trim().matches(EMAIL_REGEX))
            throw new IllegalArgumentException("");
    }

    private void validateSubject(String subject) {
        if (subject == null || subject.isEmpty())
            throw new IllegalArgumentException("");
    }

    private void validateBody(String body) {
        if (body == null || body.isEmpty())
            throw new IllegalArgumentException("");
    }
}
