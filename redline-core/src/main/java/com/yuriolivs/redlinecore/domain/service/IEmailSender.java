package com.yuriolivs.redlinecore.domain.service;

public interface IEmailSender {
    void send(Email email) throws Exception;
}
