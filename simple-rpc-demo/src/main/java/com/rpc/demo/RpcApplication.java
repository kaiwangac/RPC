package com.rpc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by kaiwang on 2016/12/28.
 */
@SpringApplicationConfiguration
public class RpcApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RpcApplication.class);
        springApplication.run(args);
    }
}
