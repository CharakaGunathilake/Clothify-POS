package repository.custom;

import dto.OrderDetail;
import entity.ProductEntity;
import repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<ProductEntity> {
    boolean updateStocks(List<OrderDetail> orderDetails);
}