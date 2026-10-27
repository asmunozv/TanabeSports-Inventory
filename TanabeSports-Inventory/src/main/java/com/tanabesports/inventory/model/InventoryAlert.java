package com.tanabesports.inventory.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
@Document(collection="inventory_alerts")
public class InventoryAlert {
 @Id private String id;
 private Long productId;
 private String message;
 private int stock;
 private Instant createdAt;
 public InventoryAlert(Long productId,String message,int stock){this.productId=productId;this.message=message;this.stock=stock;this.createdAt=Instant.now();}
 public String getId(){return id;} public Long getProductId(){return productId;} public String getMessage(){return message;} public int getStock(){return stock;} public Instant getCreatedAt(){return createdAt;}
}
