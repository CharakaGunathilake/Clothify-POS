package repository.custom.impl;

import entity.EmployeeEntity;
import entity.ProductEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ProductDao;
import javafx.collections.ObservableList;
import util.HibernateUtil;

import java.util.List;

public class ProductDaoImpl implements ProductDao {


    @Override
    public boolean save(ProductEntity product) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception sqlException) {
            if (null != transaction) {
                new Alert(Alert.AlertType.ERROR, "Failed to add Record->" + sqlException.getMessage()).show();
                transaction.rollback();
                sqlException.printStackTrace();
            }
        }finally{
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            ProductEntity entity = search(id);
            if (entity != null) {
                session.remove(entity);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (null != transaction) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete record-> " + e.getMessage()).show();
                transaction.rollback();
            }
        }finally {
            session.close();
        }return false;
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("SELECT a FROM ProductEntity a", ProductEntity.class).getResultList();
    }

    @Override
    public boolean update(ProductEntity product) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                new Alert(Alert.AlertType.ERROR, "Failed to update Record->" + sqlException.getMessage()).show();
                transaction.rollback();
                sqlException.printStackTrace();
            }
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public ProductEntity search(String id) {
        Session session = HibernateUtil.getSession();
        return session.get(ProductEntity.class, id);
    }
}
