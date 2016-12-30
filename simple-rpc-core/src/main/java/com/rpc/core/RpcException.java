package com.rpc.core;

/**
 * Created by kaiwang on 2016/12/30.
 */
public class RpcException extends Exception {
    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
}
