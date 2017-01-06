package com.rpc.core.advice;

import com.rpc.common.container.chain.HandlerChainFactory;
import com.rpc.common.utils.param.RpcRequest;
import com.rpc.common.utils.type.HandlerChainType;
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
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setInterfaceName(interfaceClass.getName());
        rpcRequest.setMethodArgs(args);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setMethodParameterTypeNames(methodParameterTypeNames);
        rpcRequest.setServiceName(referenceFactoryBean.getService());
        return new HandlerChainFactory(HandlerChainType.CONSUMER).first().handleRequest(rpcRequest);
    }

}
