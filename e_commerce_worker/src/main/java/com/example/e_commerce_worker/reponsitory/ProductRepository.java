package com.example.e_commerce_worker.reponsitory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import com.example.e_commerce_worker.document.product.ProductDocument;
@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductDocument, String> {
    
}
