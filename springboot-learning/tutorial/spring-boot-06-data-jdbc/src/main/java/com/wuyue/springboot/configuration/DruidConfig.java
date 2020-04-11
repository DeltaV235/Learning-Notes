package com.wuyue.springboot.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DruidConfig
 * @description
 * @date 2020/4/10 15:17
 */
@Configuration
public class DruidConfig {
//    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }
}
