package com.rpc.core.bean;

import com.rpc.common.RegisterException;
import com.rpc.common.register.IServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ServiceBean implements SmartLifecycle {
    private String name;
    private String protocol;
    private Object ref;
    private Class<?> interfaceClass;
    private boolean isRunning;

    @Autowired
    private IServiceRegister serviceRegister;

    @Override
    public void start() {
        this.isRunning = true;
        try {
            serviceRegister.register(name);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        this.isRunning = false;
        try {
            serviceRegister.shutdown(name);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable runnable) {
        this.isRunning = false;
    }

    @Override
    public int getPhase() {
        return 0;
    }

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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
