package com.wuyue.springboot04.message;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyMessageResolver
 * @description
 * @date 2020/4/6 23:01
 */
public class MyMessageResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("l");
        Locale locale = request.getLocale();
        if (!StringUtils.isEmpty(l)) {
            String[] s = l.split("_");
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
