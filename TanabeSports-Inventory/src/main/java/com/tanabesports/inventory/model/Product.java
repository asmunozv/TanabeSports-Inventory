package com.tanabesports.inventory.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="products", uniqueConstraints=@UniqueConstraint(columnNames="sku"))
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String sku;
 @Column(nullable=false) private String name;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal price;
 @Column(nullable=false) private int stock;
 @Column(nullable=false) private int minimumStock;
 @Version private Long version;
 protected Product() {}
 public Product(String sku,String name,BigDecimal price,int stock,int minimumStock){this.sku=sku;this.name=name;this.price=price;this.stock=stock;this.minimumStock=minimumStock;}
 public Long getId(){return id;} public String getSku(){return sku;} public String getName(){return name;} public BigDecimal getPrice(){return price;} public int getStock(){return stock;} public int getMinimumStock(){return minimumStock;}
 public void setStock(int stock){this.stock=stock;}
}
