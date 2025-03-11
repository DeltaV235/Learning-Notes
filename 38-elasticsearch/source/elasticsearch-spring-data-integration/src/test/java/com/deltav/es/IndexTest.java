package com.deltav.es;

import com.deltav.es.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Slf4j
@SpringBootTest
public class IndexTest {
    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Test
    void createIndex() {
        log.info("Created");
    }

    @Test
    void deleteIndex() {
        boolean deleteResult = restTemplate.indexOps(Product.class).delete();
        log.info("DeleteResult: {}", deleteResult);
    }
}
