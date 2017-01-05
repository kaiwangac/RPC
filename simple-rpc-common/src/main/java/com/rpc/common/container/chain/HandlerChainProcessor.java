package com.rpc.common.container.chain;

import com.rpc.common.container.chain.handler.ConsumerHandler;
import com.rpc.common.container.chain.handler.Handler;
import com.rpc.common.container.chain.handler.ProviderHandler;
import com.rpc.common.utils.type.HandlerChainType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by kaiwang on 2017/1/5.
 */
public class HandlerChainProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ProviderHandler) {
            this.process(HandlerChainType.PROVIDER, (Handler)bean);
        } else if (bean instanceof ConsumerHandler) {
            this.process(HandlerChainType.CONSUMER, (Handler) bean);
        }
        return bean;
    }

    private void process(HandlerChainType type, Handler handler) {
        List<Handler> handlerChain = HandlerChain.map.get(type);
        ListIterator<Handler> listIterator = handlerChain.listIterator();
        while (listIterator.hasNext()) {
            Handler temp = listIterator.next();
            if (temp.getOrder() > handler.getOrder()) {
                listIterator.previous().setNext(handler);
                handler.setNext(temp);
                listIterator.add(handler);
            }
        }
        if (!listIterator.hasNext()) {
            listIterator.add(handler);
        }
    }
}
