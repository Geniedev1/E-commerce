package com.example.demo.resposity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
