package repository.custom.impl;

import entity.OrderEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(orderEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception sqlException) {
            if (null != transaction) {
                new Alert(Alert.AlertType.ERROR, "Failed to add Record->" + sqlException.getMessage()).show();
                sqlException.printStackTrace();
                transaction.rollback();
            }
        }finally{
            session.close();
        }return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("SELECT a FROM OrderEntity a", OrderEntity.class).getResultList();
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(String id) {
        return null;
    }
}
