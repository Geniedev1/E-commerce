package com.example.demo.service.impservice;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.resposity.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.impservice.IUserService;
import com.example.demo.service.impservice.IProductService;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.dto.OrderDTO;
import java.util.List;
import java.util.HashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.example.demo.service.UserService;
import com.example.demo.service.ProductService;
import com.example.demo.dto.UserDTO;
import org.slf4j.LoggerFactory;
import com.example.demo.mapper.UserMapper;
@Service
public class IOrderService implements OrderService {
    OrderRepository orderRepository;
  public IOrderService(OrderRepository orderRepository) {
      this.orderRepository = orderRepository;   
      
  }
    @Override
  public OrderDTO placeOrder(OrderDTO orderDTO , UserDTO userDTO) {
    //   log.debug("Placing order for user: " + userId + " with products: " + productQuantities);
        
        Order order = OrderMapper.toEntity(orderDTO);
        order.setUser(UserMapper.toEntity(userDTO));
        orderRepository.save(order);    
        return OrderMapper.toDTO(order);
  } 
  @Override
  public void cancelOrder(Long orderId) {
//   log.debug("Cancelling order with ID: " + orderId);
    if(!orderRepository.existsById(orderId)){
        throw new OrderNotFoundException("Order with ID: " + orderId + " not found.");
    } 
    orderRepository.deleteById(orderId);
//    log.info("Order with ID: " + orderId + " has been cancelled.");
  }
     @Override 
    public OrderDTO getOrderDetails(Long orderId) {
    //    log.debug("Fetching details for order ID: " + orderId);
          Order order = orderRepository.findById(orderId).
          orElseThrow(() -> new OrderNotFoundException("Order with ID: " + orderId + " not found."));
          
          return OrderMapper.toDTO(order);
        // log.info("Order with ID: " + orderId + " not found.");
    }
}
