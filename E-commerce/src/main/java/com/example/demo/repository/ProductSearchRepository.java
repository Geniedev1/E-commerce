package com.example.demo.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import com.example.demo.dto.ProductDTO;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
@Repository
public class ProductSearchRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public ProductSearchRepository(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public  SearchHits<ProductDTO> searchProducts(String productName,int page, int size) {
        Query query = NativeQuery.builder()
            .withQuery(q -> q
                .match(m -> m
                    .field("name")
                    .query(productName)
                )
            )
            .withPageable(PageRequest.of(page, size))
            .build();

        return elasticsearchOperations.search(query, ProductDTO.class);
    }
}