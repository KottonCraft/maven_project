package com.javaee.ecard.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@PropertySource(value = "classpath:error-codes.properties", encoding = "utf-8")
@ConfigurationProperties(prefix = "error")
@Component
public class ErrorCodeProperties {
    private Map<String,String> code;
}
