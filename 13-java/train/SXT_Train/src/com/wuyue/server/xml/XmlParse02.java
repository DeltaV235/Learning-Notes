package com.wuyue.server.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParse02 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        PersonHandler personHandler = new PersonHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wuyue/server/xml/test.xml"), personHandler);

    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class PersonHandler extends DefaultHandler {
    private List<Person> persons;
    private Person person;
    private String tag; //存储当前操作的标签

    @Override
    public void startDocument() throws SAXException {
        persons = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析文档结束");
        for (Person person : persons)
            System.out.println(person);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null != qName)
            tag = qName;
        if (tag.equals("person")) {
            person = new Person();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("person")) {
            persons.add(person);
        }
        tag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length).trim();
        if (null != tag) {
            if (tag.equals("name"))
                person.setName(string);
            else if (tag.equals("age"))
                if (string.length() > 0)
                    person.setAge(Integer.parseInt(string));
        }
    }
}
