package com.xwz.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 这个做为配置类，代替xml配置文件
@ComponentScan(basePackages = {"com.xwz.aop"}) // 这个还是需要扫描的文件
public class SpringConfig {
}
