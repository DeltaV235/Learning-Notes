package com.wuyue;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ServiceProperty
 * @description
 * @date 2020/4/11 23:28
 */
@ConfigurationProperties(prefix = "wuyue.service")
public class ServiceProperty {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
