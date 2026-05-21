package com.yuriolivs.redlinecore.domain.service;

public interface EmailSenderInterface {
    boolean send(String recipient, String subject, String body);
}
