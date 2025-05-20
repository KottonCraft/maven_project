package com.javaee.ecard.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "demo.algo")
@Component
public class ECardGlobalConfig {
    private String a;
    private String b;
//    @Value("${demo.algo.a}")
//    private String aItem;
//    @Value("${demo.algo.b}")
//    private String bItem;

}
