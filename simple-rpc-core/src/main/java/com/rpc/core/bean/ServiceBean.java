package com.rpc.core.bean;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ServiceBean {
    private String name;
    private Object bean;
    private Class<?> interfaceClass;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
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
}
