package service.custom.impl;

import dto.Order;
import dto.OrderDetail;
import javafx.collections.ObservableList;
import service.custom.OrderDetailService;
import service.custom.OrderService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    @Override
    public boolean addOrderDetail(List<OrderDetail> orderDetail) {
        return false;
    }

    @Override
    public boolean deleteOrder(String id) {
        return false;
    }

    @Override
    public ObservableList<OrderDetail> getAll() {
        return null;
    }
}
