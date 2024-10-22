package repository.custom.impl;

import entity.CartTMEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.OrderDetailDao;
import util.HibernateUtil;

import java.util.List;

public class OderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(CartTMEntity orderDetailEntity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(orderDetailEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception sqlException) {
            if (null != transaction) {
                new Alert(Alert.AlertType.ERROR, "Failed to add Record->" + sqlException.getMessage()).show();
                transaction.rollback();
                sqlException.printStackTrace();
                System.out.println(sqlException.getMessage());
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
    public List<CartTMEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(CartTMEntity orderDetailEntity) {
        return false;
    }

    @Override
    public CartTMEntity search(String id) {
        return null;
    }
}
