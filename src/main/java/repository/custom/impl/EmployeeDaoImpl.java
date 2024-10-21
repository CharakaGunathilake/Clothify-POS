package repository.custom.impl;

import entity.EmployeeEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    
    @Override
    public boolean save(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(employee);
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
        public boolean delete (String id){
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            try (session) {
                EmployeeEntity entity = search(id);
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
        public List<EmployeeEntity> getAll () {
            Session session = HibernateUtil.getSession();
            return session.createQuery("SELECT a FROM EmployeeEntity a", EmployeeEntity.class).getResultList();
        }

        @Override
        public boolean update (EmployeeEntity employee){
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(employee);
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
        public EmployeeEntity search (String id){
            Session session = HibernateUtil.getSession();
            return session.get(EmployeeEntity.class, id);
        }
    }
