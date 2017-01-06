//package com.rpc.register.core;
//
//
//import com.register.client.data.RegisterInfo;
//import com.register.client.data.ServiceInfo;
//import com.register.client.registrar.RegisterClient;
//import com.rpc.common.RegisterException;
//import com.rpc.common.register.IServiceRegister;
//import com.rpc.register.property.RegisterProperty;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by kaiwang on 2017/1/6.
// */
//public class ServiceRegisterImpl implements IServiceRegister {
//    private Map<String, RegisterClient> serviceMap;
//    @Autowired
//    private RegisterInfo registerInfo;
//    @Autowired
//    private RegisterProperty registerProperty;
//
//    public ServiceRegisterImpl() {
//        this.serviceMap = new HashMap<String, RegisterClient>();
//    }
//
//    @Override
//    public void register(String serviceName) throws RegisterException {
//        if (this.serviceMap.containsKey(serviceName)) {
//            throw new RegisterException("Service has been registered!");
//        }
//        ServiceInfo serviceInfo = new ServiceInfo();
//        serviceInfo.setPort(registerProperty.getService().getPort());
//        serviceInfo.setIpAddress(registerProperty.getService().getIp());
//        serviceInfo.setServiceName(serviceName);
//        RegisterClient registerClient = new RegisterClient(serviceInfo, registerInfo);
//        this.serviceMap.put(serviceName, registerClient);
//        registerClient.register();
//    }
//
//    @Override
//    public void shutdown(String serviceName) throws RegisterException {
//        if (!this.serviceMap.containsKey(serviceName)) {
//            throw new RegisterException("Service has been shutdown!");
//        }
//        RegisterClient registerClient = this.serviceMap.get(serviceName);
//        registerClient.shutdown();
//    }
//}
