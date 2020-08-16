package com.wuyue.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MybatisConfig
 * @description
 * @date 2020/4/11 15:32
 */
@Configuration
public class MybatisConfig {
    @Bean
    public ConfigurationCustomizer customizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
