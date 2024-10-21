package repository.custom.impl;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.SupplierDao;
import javafx.collections.ObservableList;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplier) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(supplier);
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
            SupplierEntity entity = session.get(SupplierEntity.class, id);
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
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        List<SupplierEntity> supplierEntityList = session.createQuery("SELECT a FROM SupplierEntity a", SupplierEntity.class).getResultList();
        return supplierEntityList;
    }

    @Override
    public boolean update(SupplierEntity supplier) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(supplier);
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
    public SupplierEntity search(String id) {
        return null;
    }
}
