package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.PageResponse;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductSearchRepository;
import com.example.demo.service.ProductSearchService;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchServiceImpl implements ProductSearchService{
    private final ProductSearchRepository productSearchRepository;

    public ProductSearchServiceImpl(ProductSearchRepository productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }

    @Override
    public  PageResponse<ProductDTO> searchProducts(String productName,int page, int size) {
        SearchHits<ProductDTO> searchHits =  productSearchRepository.searchProducts(productName, page, size);
        return ProductMapper.toPageResponse(searchHits);
    }
}
