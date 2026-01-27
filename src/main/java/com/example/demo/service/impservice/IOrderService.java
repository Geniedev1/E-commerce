package com.example.demo.service.impservice;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.resposity.OrderRepository;
import com.example.demo.resposity.ProductRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.impservice.IUserService;
import com.example.demo.service.impservice.IProductService;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductMapper;
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
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
@Service
public class IOrderService implements OrderService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
  public IOrderService(OrderRepository orderRepository,ProductRepository productRepository) {
      this.orderRepository = orderRepository;  
        this.productRepository = productRepository;

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
    @Override
    public OrderDTO addItem(ProductDTO productDTO,int quantity, UserDTO userDTO) {
        List<Order> listorder = orderRepository.findByUserId(userDTO.getId());
        Order order = listorder.get(0);
        Product product = productRepository.findById(productDTO.getId()).
        orElseThrow(() -> new ProductNotFoundException("Product with ID: " + productDTO.getId() + " not found."));
        order.addItem(product, quantity);
        orderRepository.save(order);
        return OrderMapper.toDTO(order);
    }

}
