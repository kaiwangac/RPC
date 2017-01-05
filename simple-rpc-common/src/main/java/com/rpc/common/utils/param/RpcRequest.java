package com.rpc.common.utils.param;

import java.io.Serializable;

/**
 * Created by kaiwang on 2016/12/30.
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -6388538413186989879L;
    private String interfaceName;
    private String methodName;
    private Object[] methodArgs;
    private String[] methodParameterTypeNames;
    private String serviceName;
    private String protocol;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object[] getMethodArgs() {
        return methodArgs;
    }

    public void setMethodArgs(Object[] methodArgs) {
        this.methodArgs = methodArgs;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getMethodParameterTypeNames() {
        return methodParameterTypeNames;
    }

    public void setMethodParameterTypeNames(String[] methodParameterTypeNames) {
        this.methodParameterTypeNames = methodParameterTypeNames;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
