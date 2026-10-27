package com.tanabesports.inventory.controller;
import com.tanabesports.inventory.dto.CreateProductRequest;
import com.tanabesports.inventory.dto.StockChangeRequest;
import com.tanabesports.inventory.model.*;
import com.tanabesports.inventory.repository.InventoryAlertRepository;
import com.tanabesports.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api")
public class InventoryController {
 private final InventoryService inventory;
 private final InventoryAlertRepository alerts;
 public InventoryController(InventoryService inventory,InventoryAlertRepository alerts){this.inventory=inventory;this.alerts=alerts;}
 @PostMapping("/products") @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody CreateProductRequest r){return inventory.create(r);}
 @GetMapping("/products") public List<Product> products(){return inventory.list();}
 @GetMapping("/products/{id}") public Product product(@PathVariable Long id){return inventory.get(id);}
 @PostMapping("/products/{id}/stock") public Product change(@PathVariable Long id,@Valid @RequestBody StockChangeRequest r){return inventory.changeStock(id,r.quantity());}
 @GetMapping("/products/{id}/movements") public List<StockMovement> movements(@PathVariable Long id){return inventory.movements(id);}
 @GetMapping("/alerts") public List<InventoryAlert> alerts(){return alerts.findAll();}
}
