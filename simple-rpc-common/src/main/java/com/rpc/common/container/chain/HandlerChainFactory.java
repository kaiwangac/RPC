package com.rpc.common.container.chain;

import com.rpc.common.container.chain.handler.Handler;
import com.rpc.common.utils.type.HandlerChainType;

import java.util.List;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class HandlerChainFactory {

    private List<Handler> handlerChain;

    public HandlerChainFactory(HandlerChainType type) {
        this.handlerChain = HandlerChain.map.get(type);
    }

    public Handler first() {
        return this.handlerChain.get(0);
    }
}
