package com.rpc.common.chain;

import com.rpc.common.chain.handler.api.Handler;
import com.rpc.common.chain.type.HandlerChainType;
import com.rpc.common.param.RpcParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class HandlerChainFactory {

    private int size;
    private int cursor;
    private List<Handler> handlerChain;

    public HandlerChainFactory(HandlerChainType type) {
        this.handlerChain = HandlerChain.map.get(type);
        Collections.sort(this.handlerChain, new Comparator<Handler>() {
            @Override
            public int compare(Handler o1, Handler o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
        this.size = this.handlerChain.size();
    }

    public Object next(RpcParam rpcParam) {
        Handler handler =  this.handlerChain.get(cursor++);
        return handler.handle(this, rpcParam);
    }

    public boolean hasNext() {
        return this.cursor < this.size;
    }

    public Object start(RpcParam rpcParam) {
        Handler handler = this.handlerChain.get(0);
        cursor = 1;
        return handler.handle(this, rpcParam);
    }
}
