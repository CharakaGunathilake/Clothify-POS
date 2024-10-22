package repository.custom.impl;

import entity.OrderDetailEntity;
import repository.custom.OrderDetailDao;

import java.util.List;

public class OderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity orderDetailEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<OrderDetailEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(OrderDetailEntity orderDetailEntity) {
        return false;
    }

    @Override
    public OrderDetailEntity search(String id) {
        return null;
    }
}
