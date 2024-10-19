package repository.custom.impl;

import entity.LoginEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.custom.LoginDao;
import javafx.collections.ObservableList;
import util.HibernateUtil;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean save(LoginEntity login) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(login);
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
        }return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ObservableList<LoginEntity> getAll() {
        return null;
    }

    @Override
    public boolean update(LoginEntity login) {
        return false;
    }

    @Override
    public LoginEntity search(String id) {
        return null;
    }

    @Override
    public boolean verifyLogin(LoginEntity login) {
        return true;
    }

    @Override
    public boolean validEmail(String email) {
        return false;
    }
}
