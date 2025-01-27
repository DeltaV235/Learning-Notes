package com.deltav.order.interceptor;

import com.deltav.product.bean.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.InvocationContext;
import feign.Response;
import feign.ResponseInterceptor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class ProductNameResponseInterceptor implements ResponseInterceptor {
    @Override
    public Object intercept(InvocationContext invocationContext, Chain chain) throws Exception {
        try (Response response = invocationContext.response()) {
            StringBuilder stringBuilder = new StringBuilder();
            try (var reader = response.body().asReader(StandardCharsets.UTF_8)) {
                char[] buffer = new char[1024];
                int bytesRead;
                while ((bytesRead = reader.read(buffer)) != -1) {
                    stringBuilder.append(buffer, 0, bytesRead);
                }
            }
            String jsonResponse = stringBuilder.toString();
            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(jsonResponse, Product.class);
            product.setProductName(product.getProductName() + "-test");
            return product;
        }
    }
}
