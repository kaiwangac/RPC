package com.rpc.core.advice;

import com.rpc.common.chain.HandlerChainFactory;
import com.rpc.common.chain.type.HandlerChainType;
import com.rpc.common.param.RpcParam;
import com.rpc.core.RpcException;
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
        Class interfaceClass = referenceFactoryBean.getInterfaceClass();
        String methodName = method.getName();
        Class<?>[] methodParameterTypes = method.getParameterTypes();
        try {
            interfaceClass.getDeclaredMethod(methodName, methodParameterTypes);
        } catch (NoSuchMethodException e) {
            throw new RpcException(method.getName() + "is not the method of the interface", e);
        }
        String[] methodParameterTypeNames = new String[methodParameterTypes.length];
        for (int i = 0; i < methodParameterTypes.length; i++) {
            methodParameterTypeNames[i] = methodParameterTypes[i].getTypeName();
        }
        RpcParam rpcParam = new RpcParam();
        rpcParam.setInterfaceName(interfaceClass.getName());
        rpcParam.setMethodArgs(args);
        rpcParam.setMethodName(methodName);
        rpcParam.setMethodParameterTypeNames(methodParameterTypeNames);
        rpcParam.setServiceName(referenceFactoryBean.getService());
        return new HandlerChainFactory(HandlerChainType.CONSUMER).start(rpcParam);
    }
}
