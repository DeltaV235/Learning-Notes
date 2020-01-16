package com.wuyue.server03;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private String tag; //存储当前操作的标签
    private boolean isMapping = false;

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<>();
        mappings = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
//        System.out.println("解析文档结束");
//        for (Entity entity:entities)
//            System.out.println(entity);
//        for (Mapping mapping:mappings)
//            System.out.println(mapping);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null != qName)
            tag = qName;
        if (tag.equals("servlet")) {
            entity = new Entity();
            isMapping = false;
        } else if (tag.equals("servlet-mapping")) {
            mapping = new Mapping();
            isMapping = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("servlet")) {
            entities.add(entity);
        } else if (qName.equals("servlet-mapping")) {
            mappings.add(mapping);
        }
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length).trim();
        if (null != tag) {
            if (tag.equals("servlet-name")) {
                if (!isMapping)
                    entity.setName(string);
                else
                    mapping.setName(string);
            } else if (tag.equals("servlet-class"))
                entity.setClz(string);
            else if (tag.equals("url-pattern"))
                mapping.addPattern(string);
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}
