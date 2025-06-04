package com.ecom.ecom;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private int stockQuantity;

    // Constructors
    public Product() {}

    public Product(String name, String description, BigDecimal price, String category, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public Long getProductId() { return productId; }

    public void setProductId(Long productId) { this.productId = productId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}
