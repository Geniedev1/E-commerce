package com.example.demo.service.impservice;
import com.example.demo.resposity.OrderItemRepository;
import com.example.demo.service.OrderItemService;
import com.example.demo.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class IOrderItemService implements OrderItemService {
   OrderItemRepository orderItemRepository;
   public IOrderItemService(OrderItemRepository orderItemRepository) {
       this.orderItemRepository = orderItemRepository;
   }
   @Override
    public void orderItem(ProductDTO productDTO) {
       // Implementation logic here
            
    }
}
