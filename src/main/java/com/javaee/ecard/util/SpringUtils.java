package com.javaee.ecard.util;

import com.javaee.ecard.Application;
import com.javaee.ecard.config.ErrorCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils {
    private static ApplicationContext applicationContext;
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context){
        applicationContext = context;
    }
}
