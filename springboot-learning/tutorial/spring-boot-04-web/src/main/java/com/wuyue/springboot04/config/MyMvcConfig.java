package com.wuyue.springboot04.config;

import com.wuyue.springboot04.interceptor.LoginInterceptor;
import com.wuyue.springboot04.message.MyMessageResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyMvcConfig
 * @description
 * @date 2020/4/6 1:37
 */
//@EnableWebMvc
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/view").setViewName("success");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/",
                        "/index.html", "/user/login", "/asserts/**", "/webjars/**");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyMessageResolver();
    }
}
