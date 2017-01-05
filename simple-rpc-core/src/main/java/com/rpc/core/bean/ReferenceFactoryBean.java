package com.rpc.core.bean;

import com.rpc.core.advice.ServiceProxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ReferenceFactoryBean implements FactoryBean, InitializingBean {

    private String name;
    private String service;
    private String protocol;
    private Class<?> interfaceClass;
    private Object serviceProxy;

    public void afterPropertiesSet() throws Exception {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException(
                    interfaceClass.getName() + " is not an interface");
        }
        this.serviceProxy = Proxy.newProxyInstance(ReferenceFactoryBean.class.getClassLoader(), new Class[]{interfaceClass}, new ServiceProxy(this));
    }

    public Object getObject() throws Exception {
        return this.serviceProxy;
    }

    public Class<?> getObjectType() {
        return this.serviceProxy.getClass();
    }

    public boolean isSingleton() {
        return true;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
