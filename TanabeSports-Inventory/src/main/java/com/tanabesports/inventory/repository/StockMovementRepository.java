package com.tanabesports.inventory.repository;
import com.tanabesports.inventory.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface StockMovementRepository extends JpaRepository<StockMovement,Long> {List<StockMovement> findByProductIdOrderByCreatedAtDesc(Long productId);}
