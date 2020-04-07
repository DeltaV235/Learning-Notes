package com.wuyue.springboot04.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyErrorAttributes
 * @description
 * @date 2020/4/7 23:28
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    Logger logger = LoggerFactory.getLogger(getClass());

    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        Integer statusCode = (Integer) webRequest.getAttribute("javax.servlet.error.status_code", 0);
        logger.warn("" + statusCode);
        if (statusCode != null && statusCode.toString().startsWith("5"))
            errorAttributes.put("ext", ext);
        return errorAttributes;
    }
}
