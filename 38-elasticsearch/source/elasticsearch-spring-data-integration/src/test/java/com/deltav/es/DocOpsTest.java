package com.deltav.es;

import com.deltav.es.dao.ProductDao;
import com.deltav.es.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class DocOpsTest {
    @Autowired
    private ProductDao productDao;

    @Test
    void save() {
        Product product = Product.builder()
                .id(1L)
                .title("小米15 Ultra")
                .category("手机")
                .price(6999.99)
                .images("https://img.deltav.com/tfs/TB1_l.jpg")
                .build();
        productDao.save(product);
    }

    @Test
    void update() {
        Product product = Product.builder()
                .id(1L)
                .title("小米15 Pro")
                .category("手机")
                .price(3999.99)
                .images("https://img.deltav.com/tfs/TB2_l.jpg")
                .build();
        productDao.save(product);
    }

    @Test
    void findById() {
        Optional<Product> productOp = productDao.findById(1L);
        productOp.ifPresent(product -> log.info("Product: {}", product));
    }

    @Test
    void findAll() {
        productDao.findAll().forEach(product -> log.info("Product: {}", product));
    }

    @Test
    void delete() {
        productDao.deleteById(1L);
    }

    @Test
    void saveAll() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            productList.add(
                    Product.builder()
                            .id((long) i)
                            .title("小米%s Pro".formatted(i))
                            .category("手机")
                            .price(3999.99)
                            .images("https://img.deltav.com/tfs/TB2_l.jpg")
                            .build()
            );
        }
        productDao.saveAll(productList);
    }

    @Test
    void findByPageable() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(0, 5, sort);
        Page<Product> pageProduct = productDao.findAll(pageRequest);
        pageProduct.forEach(product -> log.info("product: {}", product));
    }
}
