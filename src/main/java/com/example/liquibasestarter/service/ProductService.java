package com.example.liquibasestarter.service;

import com.example.liquibasestarter.entity.Product;
import com.example.liquibasestarter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setStockQuantity(productDetails.getStockQuantity());
        
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    
    public List<Product> getAvailableProducts() {
        return productRepository.findByStockQuantityGreaterThan(0);
    }
    
    public List<Product> searchProducts(String searchTerm) {
        return productRepository.findByNameOrDescriptionContaining(searchTerm);
    }
    
    public List<Product> getProductsUnderPrice(BigDecimal maxPrice) {
        return productRepository.findAvailableProductsUnderPrice(maxPrice);
    }
    
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    
    public boolean updateStockQuantity(Long productId, Integer quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            int newStock = product.getStockQuantity() + quantity;
            if (newStock >= 0) {
                product.setStockQuantity(newStock);
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }
    
    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }
    
    public boolean isProductAvailable(Long id, Integer requestedQuantity) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.isPresent() && 
               productOpt.get().getStockQuantity() >= requestedQuantity;
    }
}
