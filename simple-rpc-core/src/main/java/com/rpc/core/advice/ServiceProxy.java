package com.rpc.core.advice;

import com.rpc.core.bean.ReferenceFactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ServiceProxy implements InvocationHandler {
    private ReferenceFactoryBean referenceFactoryBean;

    public ServiceProxy(ReferenceFactoryBean referenceFactoryBean) {
        this.referenceFactoryBean = referenceFactoryBean;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
