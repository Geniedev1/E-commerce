package com.example.e_commerce_worker.document.product;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
// Định nghĩa cấu trúc dữ liệu của sản phẩm trong Elasticsearch, bao gồm các trường như id, name, price, v.v. Sử dụng @Document để chỉ định rằng đây là một tài liệu Elasticsearch và @Id để đánh dấu trường id là khóa chính.
// ES cần mapping ( only api) để định dạng shcema của document nhưng ở đây định dạng đỉnh query vào ES.
@Document(indexName = "product")
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private double price;
    public ProductDocument() {
    }
    public ProductDocument(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
