package com.wuyue.server03;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Map<String, String> entitiesMap;
    private Map<String, String> mappingsMap;

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        // (servlet-name, servlet-class)
        entitiesMap = new HashMap<>();
        // (servlet-name, url-pattern)
        mappingsMap = new HashMap<>();

        // 将entities转换成Map
        for (Entity entity : entities)
            entitiesMap.put(entity.getName(), entity.getClz());
        for (Mapping mapping : mappings)
            for (String pattern : mapping.getPatterns())
                mappingsMap.put(pattern, mapping.getName());
    }

    /**
     * 通过URL的路径找到对应的class
     *
     * @param pattern
     * @return class的全路径名
     */
    public String getClz(String pattern) {
        return entitiesMap.get(mappingsMap.get(pattern));
    }
}
