package com.deltav.es.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "product", shards = 3, replicas = 1)
public class Product {
    @Id
    private Long id; //商品唯一标识

    @Field(type = FieldType.Text)
    private String title; //商品名称

    @Field(type = FieldType.Keyword)
    private String category; //分类名称

    @Field(type = FieldType.Double)
    private Double price; //商品价格

    @Field(type = FieldType.Keyword, index = false)
    private String images; //图片地址
}
