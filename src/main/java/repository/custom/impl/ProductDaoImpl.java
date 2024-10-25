package repository.custom.impl;

import entity.OrderDetailEntity;
import entity.ProductEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.ProductDao;
import util.HibernateUtil;

import java.util.List;
import java.util.Set;

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
            System.out.println(entity);
            if (entity != null) {
                System.out.println("entity");
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

    @Override
    public boolean updateStocks(Set<OrderDetailEntity> orderDetails) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            orderDetails.forEach(orderDetail -> {
                System.out.println("found Id-> " + orderDetail.getQty());
                ProductEntity product = session.get(ProductEntity.class, orderDetail.getItemCode());
                System.out.println("found-> " + product);
                if (product != null) {
                    product.setQty(product.getQty() - orderDetail.getQty());
                    System.out.println(session.merge(product));
                }
            });
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (null != transaction) {
                new Alert(Alert.AlertType.ERROR, "Failed to Update record->" + e.getMessage()).show();
                e.printStackTrace();
                transaction.rollback();
            }
        }finally {
            session.close();
        }return false;
    }
}
