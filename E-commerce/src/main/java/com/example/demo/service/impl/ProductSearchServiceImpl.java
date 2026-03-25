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
    //ES có thể query theo page nhưng cần validate page và size nếu không sẽ tốn rất nhiều tài nguyên
    //Vì ES có struct lưu các document đươi các shard phân ở các node nên mỗi query sẽ được gửi đến tất cả các shard và node để tìm kiếm (mỗi node đều queery size * page record -> nguyên nhân chính gây tốn IO), sau đó tổng hợp kết quả lại. Nếu page và size không được giới hạn, có thể dẫn đến việc truy vấn quá nhiều dữ liệu cùng một
    @Override
    public  PageResponse<ProductDTO> searchProducts(String productName,int page, int size) {
        SearchHits<ProductDTO> searchHits =  productSearchRepository.searchProducts(productName, page, size);
        return ProductMapper.toPageResponse(searchHits);
    }
}
