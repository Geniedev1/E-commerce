package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.event.EventSend.EventSend;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductSearchService;
import com.example.demo.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductSearchService productSearchService;
    private final EventSend eventSend;

    public ProductServiceImpl(ProductRepository productRepository, ProductSearchService productSearchService, EventSend eventSend) {
        this.productRepository = productRepository;
        this.productSearchService = productSearchService;
        this.eventSend = eventSend;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) {
        if (productRepository.existsByNameIgnoreCase(productDTO.getName())) {
            throw new BadRequestException("Product with name: " + productDTO.getName() + " already exists.");
        }
        Product product = ProductMapper.toEntity(productDTO);
        productRepository.save(product);
        productDTO = ProductMapper.toDTO(product);
        eventSend.toAddProduct(product);
        return productDTO;
    }

    @Override
    @Cacheable(value = "products")
    public PageResponse<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDTO> content = products.getContent().stream()
                .map(ProductMapper::toDTO)
                .toList();

        return new PageResponse<>(content, products.getNumber(), products.getSize(),
                products.getTotalElements(), products.getTotalPages(), products.isLast());
    }

    @Override
    @Cacheable(value = "products", key = "#productId")
    public double getPrice(Long productId) {
        return productRepository.findPriceById(productId);
    }

    @Override
    public boolean existsById(Long productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public PageResponse<ProductDTO> searchProducts(String keyword, int page, int size) {
        return productSearchService.searchProducts(keyword, page, size);
    }

    @Override
    @Transactional
    public void removeProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        productRepository.deleteById(productId);
        eventSend.toDeleteProduct(productId);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) {
        if (!productRepository.existsById(productDTO.getId())) {
            throw new ResourceNotFoundException("Product not found with id: " + productDTO.getId());
        }
        Product product = ProductMapper.toEntity(productDTO);
        productRepository.save(product);
        productDTO = ProductMapper.toDTO(product);
        eventSend.toUpdateProduct(product);
        return productDTO;
    }
}
