package com.example.liquibasestarter.repository;

import com.example.liquibasestarter.entity.Order;
import com.example.liquibasestarter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUser(User user);
    
    List<Order> findByUserOrderByOrderDateDesc(User user);
    
    List<Order> findByStatus(Order.OrderStatus status);
    
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status = :status")
    List<Order> findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Order.OrderStatus status);
    
    @Query("SELECT o FROM Order o WHERE o.totalAmount >= :minAmount")
    List<Order> findByTotalAmountGreaterThanEqual(@Param("minAmount") java.math.BigDecimal minAmount);
    
    List<Order> findByUserOrderByOrderDateAsc(User user);
}
