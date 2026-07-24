package com.yuriolivs.redlinecore.infrastructure.service.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mail.from")
@Getter
@Setter
public class EmailFromProperties {
    private String address;
    private String name;
}
