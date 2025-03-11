package com.deltav.es.dao;

import com.deltav.es.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {
    List<Product> searchByTitleContains(String title);

    List<Product> searchByTitle(String title);
}
