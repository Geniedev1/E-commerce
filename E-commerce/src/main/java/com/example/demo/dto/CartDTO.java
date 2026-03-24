package com.example.demo.dto;

import com.example.demo.repository.CartItemWithPriceProjection;
import org.springframework.data.domain.Page;
import java.util.List;
public class CartDTO {
    private Long id;
    private Long userId;
    private List<CartItemDTO> items;

    public CartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    private double totalAmount;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
    public void setInforPage(Page<CartItemWithPriceProjection> pagedCartItems){
        this.setLast(pagedCartItems.isLast());
        this.setTotalPages(pagedCartItems.getTotalPages());
        this.setTotalElements(pagedCartItems.getTotalElements());
        this.setPageSize(pagedCartItems.getSize());
        this.setPageNo(pagedCartItems.getNumber());
    }
}
