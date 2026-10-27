package com.tanabesports.inventory.repository;
import com.tanabesports.inventory.model.InventoryAlert;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface InventoryAlertRepository extends MongoRepository<InventoryAlert,String> {}
