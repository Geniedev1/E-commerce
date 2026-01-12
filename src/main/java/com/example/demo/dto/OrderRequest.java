package com.example.demo.dto;
import java.util.Map;

import com.example.demo.model.Product;

import java.util.List;
public class OrderRequest {
     private String userId; 
     private List<ProductsRequest> products;
     public OrderRequest (){

     }
     public OrderRequest(String userId,List<ProductsRequest> products ) {
       this.userId = userId;
      this.products = products;
     }
     public String getUserId() {
        return userId;
    }
       public List<ProductsRequest> getProducts() {
           return products;
       }
       public void setUserId(String userId) {
           this.userId = userId;
       }
        public void setProducts(List<ProductsRequest> products) {
            this.products = products;
        }
     
}
