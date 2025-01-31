package com.deltav.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.deltav.common.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class SentinelBlockExceptionHandler implements BlockExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String resourceName, BlockException e) throws Exception {
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        Response<Object> failResp = Response.fail(500, resourceName + ", request blocked by Sentinel, exception: " + e);
        String failJson = objectMapper.writeValueAsString(failResp);
        writer.write(failJson);
        writer.flush();
        writer.close();
    }
}
