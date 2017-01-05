package com.rpc.common.container.chain.handler;

import com.rpc.common.utils.param.RpcRequest;

/**
 * Created by kaiwang on 2016/12/28.
 */
public abstract class Handler {

    private int order;
    private Handler next;
    private String protocol;

    protected void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    protected void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Object handleRequest(RpcRequest rpcRequest) {
        if (this.protocol == null) {
            return handle(rpcRequest);
        } else {
            if (this.protocol.equals(rpcRequest.getProtocol())) {
                return handle(rpcRequest);
            } else {
                return this.next.handleRequest(rpcRequest);
            }
        }
    }

    protected abstract Object handle(RpcRequest rpcRequest);
}
