package com.rpc.loadbalance.handler;

import com.rpc.common.container.chain.handler.ConsumerHandler;
import com.rpc.common.utils.param.RpcRequest;

/**
 * Created by kaiwang on 2017/1/6.
 */
public class LoadBalanceHandler extends ConsumerHandler {

    public LoadBalanceHandler() {
        setOrder(50);
    }

    @Override
    protected Object handle(RpcRequest rpcRequest) {

        return this.getNext().handleRequest(rpcRequest);
    }
}
