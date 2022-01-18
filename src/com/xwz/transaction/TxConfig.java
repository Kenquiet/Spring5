package com.xwz.transaction;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ResourceBundle;

/**
 * 完全注解的方式，就是对 transaction.xml 进行注解化
 * */
@Configuration // 配置类
@ComponentScan(basePackages = "com.xwz.transaction") // 开启扫描
@EnableTransactionManagement // 开启事务
public class TxConfig {
  // 获取配置文件
  String resourceFile = "com.xwz.transaction.jdbc";
  ResourceBundle bundle = ResourceBundle.getBundle(resourceFile);

 // 配置连接池
  @Bean
  public DruidDataSource getDruidDataSource() {
    DruidDataSource druidDataSource = new DruidDataSource();
    druidDataSource.setDriverClassName(bundle.getString("prop.Driver"));
    druidDataSource.setUrl(bundle.getString("prop.Url"));
    druidDataSource.setUsername(bundle.getString("prop.user"));
    druidDataSource.setPassword(bundle.getString("prop.password"));
    return  druidDataSource;
  }

  // 配置jdbcTemplate
  @Bean
  public JdbcTemplate getJdbcTemplate(DruidDataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
  }

  // 创建事务管理器
  @Bean
  public DataSourceTransactionManager getDataSourceTransactionManager(DruidDataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(dataSource);
    return  transactionManager;
  }
}
