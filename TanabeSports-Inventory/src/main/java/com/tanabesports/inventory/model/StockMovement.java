package com.tanabesports.inventory.model;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="stock_movements")
public class StockMovement {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long productId;
 @Column(nullable=false) private int changeQuantity;
 @Column(nullable=false) private int resultingStock;
 @Column(nullable=false) private Instant createdAt;
 protected StockMovement(){}
 public StockMovement(Long productId,int changeQuantity,int resultingStock){this.productId=productId;this.changeQuantity=changeQuantity;this.resultingStock=resultingStock;this.createdAt=Instant.now();}
 public Long getId(){return id;} public Long getProductId(){return productId;} public int getChangeQuantity(){return changeQuantity;} public int getResultingStock(){return resultingStock;} public Instant getCreatedAt(){return createdAt;}
}
