package com.rpc.core.bean;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ServiceBean {
    private String name;
    private Object ref;
    private Class<?> interfaceClass;

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
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
