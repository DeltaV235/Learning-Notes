package com.wuyue.view;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyViewResolver
 * @description
 * @date 2020/3/24 17:46
 */
public class MyViewResolver implements ViewResolver, Ordered {
    private Integer order;

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("wuyue:")) {
            return new MyView();
        }
        return null;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}














