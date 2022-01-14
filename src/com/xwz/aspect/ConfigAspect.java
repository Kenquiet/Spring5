package com.xwz.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 全注解形式，不需要Aop.xml
@Configuration
@ComponentScan(basePackages = {"com.xwz.aspect"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAspect {
}
