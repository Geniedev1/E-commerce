package com.example.e_commerce_worker.reponsitory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.example.e_commerce_worker.document.product.ProductDocument;
// Connect và query dữ liệu từ Elasticsearch, tương tác với ProductDocument để lưu trữ và truy xuất thông tin sản phẩm.
@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductDocument, String> {
    
}
