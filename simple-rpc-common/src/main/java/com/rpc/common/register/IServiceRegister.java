package com.rpc.common.register;

import com.rpc.common.RegisterException;

/**
 * Created by kaiwang on 2017/1/6.
 */
public interface IServiceRegister {
    void register(String serviceName) throws RegisterException;

    void shutdown(String serviceName) throws RegisterException;
}
