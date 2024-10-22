package service.custom.impl;

import dto.Employee;
import dto.Order;
import entity.EmployeeEntity;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import service.custom.OrderService;
import util.DaoType;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = DaoFactory.getInstance().getServiceType(DaoType.ORDER);

    @Override
    public boolean addOrder(Order order) {
        OrderEntity entity = new ModelMapper().map(order, OrderEntity.class);
        return orderDao.save(entity);
    }

    @Override
    public boolean deleteOrder(String id) {
        return orderDao.delete(id);
    }

    @Override
    public ObservableList<Order> getAll() {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        orderDao.getAll().forEach(orderEntity -> {
            System.out.println(orderEntity);
            orderList.add(new ModelMapper().map(orderEntity, Order.class));
        });
        return orderList;
    }

    @Override
    public ObservableList<String> getOrderIds() {
        ObservableList<String> orderIdList = FXCollections.observableArrayList();
        orderDao.getAll().forEach(orderEntity -> {
            orderIdList.add(orderEntity.getOrderId());
        });
        return orderIdList;
    }

    @Override
    public String generateId() {
        List<String> orderIdList = getOrderIds();
        if (!orderIdList.isEmpty()) {
            String last = orderIdList.get((orderIdList.size())-1);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(last);
            Integer id = null;
            while (m.find()) {
                id = Integer.parseInt(m.group());
            }
            if (id < 10) {
                return "O00" + (id + 1);
            } else if (id < 100) {
                return "O0" + (id + 1);
            } else {
                return "O" + (id + 1);
            }
        }else {
            return "O001";
        }
    }
}
