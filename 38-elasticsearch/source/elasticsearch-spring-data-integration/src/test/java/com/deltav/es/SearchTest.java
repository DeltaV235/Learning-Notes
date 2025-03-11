package com.deltav.es;

import com.deltav.es.dao.ProductDao;
import com.deltav.es.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
@SpringBootTest
public class SearchTest {
    @Autowired
    private ProductDao productDao;

    @Test
    void searchQuery() {
        Pageable pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "id"));
        List<Product> productList = productDao.searchByTitle("小米7");
        productList.forEach(product -> log.info("Product: {}", product));
    }
}
