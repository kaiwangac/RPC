package com.rpc.core.bean;

import com.rpc.common.scanner.ClassScanner;
import com.rpc.core.annotation.Reference;
import com.rpc.core.annotation.Service;
import com.rpc.core.property.ReferenceProperty;
import com.rpc.core.property.ServiceProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by kaiwang on 2016/12/29.
 */
public class AnnotationBean implements BeanPostProcessor, BeanFactoryPostProcessor {
    private String basePackage;
    private ConfigurableListableBeanFactory beanFactory;

    public AnnotationBean(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        List<Class> classList = null;
        try {
            classList = ClassScanner.doScan(this.basePackage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Class clazz : classList) {
            if (clazz.isAnnotationPresent(Service.class)) {
                Service service = (Service) clazz.getDeclaredAnnotation(Service.class);
                registerServiceBean(service, clazz);
            }
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Reference.class)) {
                Reference reference = (Reference) field.getDeclaredAnnotation(Reference.class);
                registerReferenceFactoryBean(reference, field);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    field.set(bean, beanFactory.getBean(reference.name()));
                } catch (IllegalAccessException e) {
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void registerServiceBean(Service service, Class clazz) {
        BeanDefinitionBuilder serviceBean = BeanDefinitionBuilder.genericBeanDefinition(ServiceBean.class);
        serviceBean.addPropertyValue(ServiceProperty.NAME, service.name());
        BeanDefinitionBuilder ref = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(clazz.getName(), ref.getBeanDefinition());
        serviceBean.addPropertyReference(ServiceProperty.REF, clazz.getName());
        serviceBean.addPropertyValue(ServiceProperty.INTERFACE_CLASS, clazz.getInterfaces().length == 0 ? null : clazz.getInterfaces()[0]);
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(service.name(), serviceBean.getBeanDefinition());
    }

    private void registerReferenceFactoryBean(Reference reference, Field field) {
        BeanDefinitionBuilder referenceFactoryBean = BeanDefinitionBuilder.genericBeanDefinition(ReferenceFactoryBean.class);
        referenceFactoryBean.addPropertyValue(ReferenceProperty.NAME, reference.name());
        referenceFactoryBean.addPropertyReference(ReferenceProperty.SERVICE, reference.service());
        referenceFactoryBean.addPropertyValue(ReferenceProperty.INTERFACE_CLASS, field.getType());
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(reference.name(), referenceFactoryBean.getBeanDefinition());
    }
}
