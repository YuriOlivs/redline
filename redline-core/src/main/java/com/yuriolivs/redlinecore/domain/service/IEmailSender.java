package com.yuriolivs.redlinecore.domain.service;

public interface IEmailSender {
    boolean send(String recipient, String subject, String body);
}
