package com.wuyue.springboot04.config;

import com.wuyue.springboot04.filter.myFilter;
import com.wuyue.springboot04.interceptor.LoginInterceptor;
import com.wuyue.springboot04.listener.myListener;
import com.wuyue.springboot04.message.MyMessageResolver;
import com.wuyue.springboot04.servlet.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

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
                        "/index.html", "/success", "/user/login", "/asserts/**", "/webjars/**", "/error");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyMessageResolver();
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(19090);
            }
        };
    }

    @Bean
    public ServletListenerRegistrationBean<myListener> myListener() {
        return new ServletListenerRegistrationBean<>(new myListener());
    }

    @Bean
    public FilterRegistrationBean<myFilter> myFilter() {
        FilterRegistrationBean<myFilter> myFilter = new FilterRegistrationBean<>();
        myFilter.setFilter(new myFilter());
        myFilter.setUrlPatterns(Arrays.asList("/myServlet", "/hello"));
        return myFilter;
    }

    @Bean
    public ServletRegistrationBean<MyServlet> myServlet() {
        return new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
    }
}
