package com.wuyue.server.servlet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <servlet-mapping>
 *         <servlet-name>login</servlet-name>
 *         <url-pattern>/login</url-pattern>
 *         <url-pattern>/g</url-pattern>
 *  </servlet-mapping>
 */
public class Mapping {
    private String name;
    private Set<String> patterns;

    public Mapping() {
        patterns = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<String> patterns) {
        this.patterns = patterns;
    }

    public void addPattern(String pattern) {
        this.patterns.add(pattern);
    }

    @Override
    public String toString() {
        return "servlet-mapping{" +
                "servlet-name='" + name + '\'' +
                ", url-pattern=" + Arrays.toString(patterns.toArray()) +
                '}';
    }
}
