package com.example.liquibasestarter.repository;

import com.example.liquibasestarter.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
    
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Product> findByStockQuantityGreaterThan(Integer minStock);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchTerm% OR p.description LIKE %:searchTerm%")
    List<Product> findByNameOrDescriptionContaining(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT p FROM Product p WHERE p.price <= :maxPrice AND p.stockQuantity > 0")
    List<Product> findAvailableProductsUnderPrice(@Param("maxPrice") BigDecimal maxPrice);
    
    List<Product> findByNameContainingIgnoreCase(String name);
}
