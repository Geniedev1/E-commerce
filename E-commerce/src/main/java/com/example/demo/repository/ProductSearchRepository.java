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
//query gần đúng với productName, với field name của document trong ES, và sử dụng pageable để phân trang kết quả trả về.
//SearchHits<ProductDTO> sẽ chứa các kết quả tìm kiếm, bao gồm thông tin về sản phẩm và điểm số liên quan
// đó là lý do nó trả về SearchHits<ProductDTO> thay vì List<ProductDTO>, để có thể truy cập thêm thông tin về điểm số và các metadata khác của kết quả tìm kiếm.Khác với Page vì 2 đối tượng này trả về metadata nhưng khác nhau
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