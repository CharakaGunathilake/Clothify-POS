package service.custom.impl;

import dto.Order;
import dto.OrderDetail;
import entity.OrderDetailEntity;
import entity.OrderEntity;
import entity.ProductEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDao;
import repository.custom.OrderDetailDao;
import repository.custom.ProductDao;
import service.custom.OrderService;
import util.DaoType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderServiceImpl implements OrderService {

    ProductDao productDao = DaoFactory.getInstance().getServiceType(DaoType.PRODUCT);
    OrderDao orderDao = DaoFactory.getInstance().getServiceType(DaoType.ORDER);
    OrderDetailDao orderDetailDao = DaoFactory.getInstance().getServiceType(DaoType.ORDERDETAIL);

    @Override
    public boolean addOrder(Order order) {
        Set<OrderDetailEntity> orderDetailSet = new HashSet<>();
        order.getOrderDetails().forEach(orderDetail -> {
            orderDetailSet.add(new ModelMapper().map(orderDetail, OrderDetailEntity.class));
        });
        if (productDao.updateStocks(orderDetailSet)) {
            if (addOrderDetails(order.getOrderDetails())) {
                return orderDao.save(new ModelMapper().map(order, OrderEntity.class));
            }
        }
        return false;
    }

    private boolean addOrderDetails(Set<OrderDetail> cartTMEntities) {
        for (OrderDetail orderDetail : cartTMEntities) {
            OrderDetailEntity orderDetailEntity = new ModelMapper().map(orderDetail, OrderDetailEntity.class);
            boolean isDone = orderDetailDao.save(orderDetailEntity);
            if (!isDone) {
                return false;
            }
        }
        return true;
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
            String last = orderIdList.get((orderIdList.size()) - 1);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(last);
            Integer id = null;
            while (m.find()) {
                id = Integer.parseInt(m.group());
            }
            return String.format("O%03d", (id + 1));
        } else {
            return "O001";
        }
    }
}
