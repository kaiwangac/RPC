package com.rpc.common.chain;

/**
 * Created by kaiwang on 2016/12/28.
 */
public abstract class Node implements Comparable<Node> {

    private int order;

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int compareTo(Node o) {
        return this.getOrder() - o.getOrder();
    }

    public abstract void preHandle(Node node);
    public abstract Object postHandle();
}
