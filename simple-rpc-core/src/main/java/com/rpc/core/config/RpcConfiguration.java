package com.rpc.core.config;

import com.rpc.core.property.RpcProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kaiwang on 2016/12/28.
 */
@Configuration
public class RpcConfiguration {
    @Bean
    public RpcProperty rpcProperty() {
        return new RpcProperty();
    }
}
