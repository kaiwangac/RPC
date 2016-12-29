package com.rpc.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by kaiwang on 2016/12/14.
 */
public class ContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private static ApplicationContext getApplicationContext() {
        Assert.notNull(applicationContext, "Application context has not been injected!");
        return applicationContext;
    }

    public static Object getBean(String var1) throws BeansException {
        return getApplicationContext().getBean(var1);
    }

    public static <T> T getBean(String var1, Class<T> var2) throws BeansException {
        return getApplicationContext().getBean(var1, var2);
    }

    public static <T> T getBean(Class<T> var1) throws BeansException {
        return getApplicationContext().getBean(var1);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getApplicationContext().getBeansOfType(type);
    }

    public static boolean containsBean(String var1) {
        return getApplicationContext().containsBean(var1);
    }
}
