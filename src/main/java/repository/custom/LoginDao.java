package repository.custom;

import entity.LoginEntity;
import repository.CrudRepository;

public interface LoginDao extends CrudRepository<LoginEntity> {
    boolean verifyLogin(LoginEntity login);
    boolean validEmail(String email);
}