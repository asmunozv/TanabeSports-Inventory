package com.tanabesports.inventory.service;
import com.tanabesports.inventory.dto.CreateProductRequest;
import com.tanabesports.inventory.model.Product;
import com.tanabesports.inventory.model.StockMovement;
import com.tanabesports.inventory.observer.StockChangedEvent;
import com.tanabesports.inventory.repository.ProductRepository;
import com.tanabesports.inventory.repository.StockMovementRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@Service
public class InventoryService {
 private final ProductRepository products;
 private final StockMovementRepository movements;
 private final ApplicationEventPublisher publisher;
 public InventoryService(ProductRepository products,StockMovementRepository movements,ApplicationEventPublisher publisher){this.products=products;this.movements=movements;this.publisher=publisher;}
 @Transactional public Product create(CreateProductRequest r){return products.save(new Product(r.sku(),r.name(),r.price(),r.stock(),r.minimumStock()));}
 @Transactional(readOnly=true) public List<Product> list(){return products.findAll();}
 @Transactional(readOnly=true) public Product get(Long id){return products.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"));}
 @Transactional(readOnly=true) public List<StockMovement> movements(Long id){get(id);return movements.findByProductIdOrderByCreatedAtDesc(id);}
 @Transactional public Product changeStock(Long id,int quantity){
  if(quantity==0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La cantidad no puede ser cero");
  Product p=get(id);
  int before=p.getStock(); long result=(long)before+quantity;
  if(result<0 || result>Integer.MAX_VALUE)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Stock inválido o insuficiente");
  p.setStock((int)result);
  movements.save(new StockMovement(id,quantity,p.getStock()));
  StockChangedEvent event=new StockChangedEvent(id,p.getName(),before,p.getStock(),p.getMinimumStock());
  TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
   @Override public void afterCommit(){publisher.publishEvent(event);}
  });
  return p;
 }
}
