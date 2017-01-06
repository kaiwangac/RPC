package com.rpc.common.config;

import com.rpc.common.container.chain.HandlerChainProcessor;
import com.rpc.common.container.context.ContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kaiwang on 2016/12/29.
 */
@Configuration
public class CommonConfiguration {
    @Bean
    public ContextHolder contextHolder() {
        return new ContextHolder();
    }

    @Bean
    public HandlerChainProcessor handlerChainProcessor() {
        return new HandlerChainProcessor();
    }
}
