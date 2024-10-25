package repository.custom.impl;

import entity.OrderDetailEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.OrderDetailDao;
import util.HibernateUtil;

import java.util.List;

public class OderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity orderDetailEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(orderDetailEntity);
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
