package com.wuyue.ajax.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuyue.ajax.jackson.Person;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JackSonTest
 * @description
 * @date 2020/2/23 14:22
 */
public class JackSonTest {
    @Test
    public void jacksonDemo1() throws IOException {
        Person person = new Person();
        person.setName("wuyue");
        person.setAge(18);
        person.setGender("male");
        person.setBirthday(new Date());

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
        System.out.println(json);
        mapper.writeValue(new File("test.json"), person);
    }

    @Test
    public void test2() throws IOException {
        String json = "{\"name\":\"wuyue\",\"age\":18,\"gender\":\"male\",\"birthday\":\"2020-02-23 10:55:30\"}";
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);
    }
}
