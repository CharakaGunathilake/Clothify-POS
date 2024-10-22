package service.custom;

import dto.Employee;
import dto.Order;
import javafx.collections.ObservableList;
import service.SuperService;

public interface OrderService extends SuperService {
    boolean addOrder(Order order);
    boolean deleteOrder(String id);
    ObservableList<Order> getAll();
    ObservableList<String> getOrderIds();
    String generateId ();
}
