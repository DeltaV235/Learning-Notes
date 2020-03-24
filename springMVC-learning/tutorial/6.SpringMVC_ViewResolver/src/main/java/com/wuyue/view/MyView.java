package com.wuyue.view;

import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MyView
 * @description
 * @date 2020/3/24 17:47
 */
public class MyView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        for (Map.Entry<String, ?> stringEntry : model.entrySet()) {
            if (stringEntry.getValue() instanceof String)
                writer.write((String) stringEntry.getValue());
            else
                System.out.println(stringEntry.getValue());
        }
    }
}














