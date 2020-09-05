package com.wuyue.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className queryController
 * @description
 * @date 2020/4/9 23:51
 */
@Controller
public class queryController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/query")
    public Map<String, Object> query() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from department");
        return maps.get(0);
    }
}
