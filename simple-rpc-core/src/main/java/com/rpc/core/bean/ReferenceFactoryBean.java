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
    private Class<?> interfaceClass;
    private Object serviceProxy;

    public void afterPropertiesSet() throws Exception {
        this.serviceProxy = Proxy.newProxyInstance(ReferenceFactoryBean.class.getClassLoader(), new Class[]{interfaceClass}, new ServiceProxy(this));
    }

    public Object getObject() throws Exception {
        return this.serviceProxy;
    }

    public Class<?> getObjectType() {
        return interfaceClass;
    }

    public boolean isSingleton() {
        return false;
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
}
