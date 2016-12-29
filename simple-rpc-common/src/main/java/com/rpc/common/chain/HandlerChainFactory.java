package com.rpc.common.chain;

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

    public Handler next() {
        return this.handlerChain.get(cursor++);
    }

    public boolean hasNext() {
        return this.cursor < this.size;
    }
}
