package com.rpc.common.chain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kaiwang on 2016/12/28.
 */
public class ChainFactory {
    @Autowired
    private Node[] chain;

    @PostConstruct
    public void init() {
        Arrays.sort(chain, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getOrder() - o2.getOrder();
            }
        });
    }
}
