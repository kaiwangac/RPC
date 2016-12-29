package com.rpc.common.chain;

/**
 * Created by kaiwang on 2016/12/28.
 */
public abstract class Handler {

    private int order;

    protected void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public abstract Object handle();
}
