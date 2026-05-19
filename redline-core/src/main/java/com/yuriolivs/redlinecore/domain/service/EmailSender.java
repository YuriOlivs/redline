package com.yuriolivs.redlinecore.domain.service;

public interface EmailSender {
    boolean send(String recipient, String subject, String body);
}
