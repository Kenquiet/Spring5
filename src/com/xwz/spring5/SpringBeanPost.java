package com.xwz.spring5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SpringBeanPost implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("这是第三步");
    return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("这是第五步");
    return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
