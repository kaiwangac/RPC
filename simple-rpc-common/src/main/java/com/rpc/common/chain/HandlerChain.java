package com.rpc.common.chain;

import com.rpc.common.chain.handler.api.Handler;
import com.rpc.common.chain.type.HandlerChainType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class HandlerChain {
    public static final HashMap<HandlerChainType, List<Handler>> map = new HashMap<>();

    static {
        map.put(HandlerChainType.CONSUMER, new ArrayList<Handler>());
        map.put(HandlerChainType.PROVIDER, new ArrayList<Handler>());
    }
}
