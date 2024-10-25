package repository.custom;

import entity.OrderDetailEntity;
import entity.ProductEntity;
import repository.CrudRepository;

import java.util.Set;

public interface ProductDao extends CrudRepository<ProductEntity> {
    boolean updateStocks(Set<OrderDetailEntity> orderDetails);
}