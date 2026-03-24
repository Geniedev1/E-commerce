package com.example.demo.mapper;

import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductDTO;
import com.example.demo.event.product.ProductDeleteEvent;
import com.example.demo.event.product.ProductAddEvent;
import com.example.demo.event.product.ProductUpdateEvent;
import com.example.demo.model.Product;
import org.springframework.data.elasticsearch.core.SearchHit;
import java.util.List;
import org.springframework.data.elasticsearch.core.SearchHits;

public class ProductMapper {
     public static Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }  
    public static Product toEntitywithId(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return product;
    }
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
    public static ProductDeleteEvent toDeleteEvent(Long productId) {
        return new ProductDeleteEvent(productId);
    }
    public static ProductAddEvent toAddEvent(ProductDTO productDTO) {
      ProductAddEvent productEvent = new ProductAddEvent();
        productEvent.setId(productDTO.getId());
        productEvent.setName(productDTO.getName());
        productEvent.setPrice(productDTO.getPrice());
        return productEvent;
    }
    public static ProductAddEvent toAddEvent(Product product) {
        ProductAddEvent productEvent = new ProductAddEvent();
        productEvent.setId(product.getId());
        productEvent.setName(product.getName());
        productEvent.setPrice(product.getPrice());
        return productEvent;
    }
    public static ProductUpdateEvent toUpdateEvent(ProductDTO productDTO) {
        ProductUpdateEvent productEvent = new ProductUpdateEvent();
        productEvent.setId(productDTO.getId());
        productEvent.setPrice(productDTO.getPrice());
        return productEvent;
    }
        public static ProductUpdateEvent toUpdateEvent(Product product) {
            ProductUpdateEvent productEvent = new ProductUpdateEvent();
            productEvent.setId(product.getId());
            productEvent.setPrice(product.getPrice());
            return productEvent;
        }
   public static PageResponse<ProductDTO> toPageResponse(SearchHits<ProductDTO> searchHits) {
        List<ProductDTO> content = searchHits.getSearchHits()
              .stream()
              .map(SearchHit::getContent)
              .toList();
              long totalElements = searchHits.getTotalHits();
              return new PageResponse<>(content,totalElements);
    }
}
