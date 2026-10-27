package com.tanabesports.inventory.observer;
import com.tanabesports.inventory.model.InventoryAlert;
import com.tanabesports.inventory.repository.InventoryAlertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
@Component
public class LowStockObserver {
 private static final Logger log=LoggerFactory.getLogger(LowStockObserver.class);
 private final InventoryAlertRepository alerts;
 public LowStockObserver(InventoryAlertRepository alerts){this.alerts=alerts;}
 @EventListener
 public void onStockChanged(StockChangedEvent event){
  if(event.newStock()<=event.minimumStock()){
   String message="Stock bajo: "+event.productName()+" ("+event.newStock()+" unidades)";
   alerts.save(new InventoryAlert(event.productId(),message,event.newStock()));
   log.warn(message);
  }
 }
}
