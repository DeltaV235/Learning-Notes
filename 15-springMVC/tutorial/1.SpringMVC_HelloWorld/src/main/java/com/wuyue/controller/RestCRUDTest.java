package com.wuyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DeltaV235
 * @version 1.0
 * @className RestCRUDTest
 * @description
 * @date 2020/3/21 2:22
 */
@Controller
public class RestCRUDTest {

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    public String getBook(@PathVariable("id") Integer id) {
        System.out.println("查询图书 id = " + id);
        return "success";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public String delBook(@PathVariable("id") Integer id) {
        System.out.println("删除图书 id = " + id);
        return "success";
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public String updateBook(@PathVariable("id") Integer id) {
        System.out.println("更新图书 id = " + id);
        return "success";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String insertBook() {
        System.out.println("增加图书");
        return "success";
    }
}














