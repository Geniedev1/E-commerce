package com.example.demo.service.impservice;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.exception.ProductNotFoundException;
import java.util.List;
import com.example.demo.dto.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class IProductService implements ProductService {
    private List<Product> products; 
    public IProductService() {
        // Initialize with some products
     this.products = new java.util.ArrayList<>();
    }
    @Override
    public void checkProduct(String productId) {
       boolean exists = products.stream()
                .anyMatch(product -> product.getId().toString().equals(productId));
    if(!exists) {
       throw new ProductNotFoundException(productId);
    }
  }
  @Override
  public Product addProduct(ProductRequest productRequest) {
      Product product = new Product(productRequest.getId(), productRequest.getName(), productRequest.getPrice());
      products.add(product);
      return product;    
  }
  @Override
   public List<Product> getAllProducts() {
         return products;
   }
}
