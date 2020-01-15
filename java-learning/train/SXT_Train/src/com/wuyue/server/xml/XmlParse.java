package com.wuyue.server.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class XmlParse {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        PersonHandler pHandler = new PersonHandler();
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/wuyue/server/xml/test.xml"), pHandler);
    }
}

class PHandler extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("解析文档开始");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析文档结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + " 解析开始");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + " 解析结束");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string = new String(ch, start, length).trim();
        if (string.length() > 0)
            System.out.println("内容为:" + string);
    }
}
