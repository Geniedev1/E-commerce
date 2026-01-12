package com.example.demo.service.impservice;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.impservice.IUserService;
import com.example.demo.service.impservice.IProductService;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.dto.OrderRequest;
import com.example.demo.dto.ProductsRequest;
import java.util.List;
import java.util.HashMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.example.demo.service.UserService;
import com.example.demo.service.ProductService;
@Service
public class IOrderService implements OrderService {
    UserService userService;
    ProductService productService;
  private List<Order> orders;  
  private List<User> users;    
  public IOrderService(UserService userService, ProductService productService) {
      this.orders = new java.util.ArrayList<>();
      this.users = new java.util.ArrayList<>();
      this.userService = userService;
      this.productService = productService;
  }
    @Override
  public Order placeOrder(OrderRequest orderRequest ) {
    //   log.debug("Placing order for user: " + userId + " with products: " + productQuantities);
      
      HashMap<String, Integer> productQuantities = new HashMap<>();
     for(ProductsRequest productsRequest : orderRequest.getProducts()) {
         String productId = productsRequest.getProductId();
         System.out.println(productService.getAllProducts().size());
         productService.checkProduct(productId);
         userService.checkUser(orderRequest.getUserId());
         productQuantities.put(productId, productsRequest.getQuantity());
      }
      Order newOrder;
      if(orders.size() != 0) {
                 newOrder = new Order(Long.toString(Long.parseLong(orders.getLast().getOrderId()) + 1), orderRequest.getUserId(), productQuantities);
      } else {
                  newOrder = new Order("1", orderRequest.getUserId(), productQuantities);
      }
       orders.add(newOrder);
       return newOrder;
  } 
  @Override
  public void cancelOrder(String orderId) {
//   log.debug("Cancelling order with ID: " + orderId);
     for(Order order : orders) {
         if(order.getOrderId().equals(orderId)) {
             orders.remove(order);
             return;
            }
     }
        // log.info("Order with ID: " + orderId + " not found for cancellation.");
        throw new OrderNotFoundException(orderId);
    }
     @Override 
    public Order getOrderDetails(String orderId) {
    //    log.debug("Fetching details for order ID: " + orderId);
        for(Order order : orders) {
            if(order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        // log.info("Order with ID: " + orderId + " not found.");
        throw new OrderNotFoundException(orderId);
  }

}

