package service.custom;

import dto.OrderDetail;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface OrderDetailService extends SuperService {
    boolean addOrderDetail(List<OrderDetail> orderDetail);
    boolean deleteOrder(String id);
    ObservableList<OrderDetail> getAll();}
