package com.rpc.core.property;

import org.springframework.beans.factory.annotation.Value;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * Created by kaiwang on 2017/1/6.
 */
public class RpcProperty {
    private static final int PORT_MIN = 0;
    private static final int PORT_MAX = (1 << 16) - 1;
    @Value("{rpc.service.http.ip:127.0.0.1}")
    private String httpIp;
    @Value("{rpc.service.http.port:0}")
    private int httpPort;

    public String getHttpIp() {
        return httpIp;
    }

    public void setHttpIp(String httpIp) {
        this.httpIp = httpIp;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    private boolean isIpAvailable(String ip) {
        try {
            Inet4Address.getByName(ip);
        } catch (UnknownHostException e) {
            return false;
        }
        return true;
    }

    private boolean isPortAvailable(int port) {
        return PORT_MIN < port && port <= PORT_MAX;
    }
}
