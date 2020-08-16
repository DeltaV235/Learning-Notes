package com.wuyue.redis.web.servlet;

import com.wuyue.redis.service.ProvinceService;
import com.wuyue.redis.service.impl.ProvinceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className FindProvince
 * @description 负责处理查询省份相关数据
 * @date 2020/2/24 13:23
 */
@WebServlet("/FindProvince")
public class FindProvince extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProvinceService service = new ProvinceServiceImpl();
//        List<Province> list = service.findAll();
//        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(resp.getWriter(), list);
        String allJson = service.findAllJson();
        resp.getWriter().write(allJson);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
