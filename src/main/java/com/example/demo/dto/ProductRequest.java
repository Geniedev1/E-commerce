package com.example.demo.dto;

public class ProductRequest {
  private String id;
  private String name;
  private double price;
  public ProductRequest(String id, String name, double price) {
      this.id = id;
      this.name = name;
      this.price = price;
  }
  public ProductRequest() {
  }
  public String getId() {
      return id;
  }
  public String getName() {
      return name;
  }
  public double getPrice() {
      return price;
  }
  public void setId(String id) {
      this.id = id;
  }
  public void setName(String name) {
      this.name = name;
  }
  public void setPrice(double price) {
      this.price = price;
  }


}
