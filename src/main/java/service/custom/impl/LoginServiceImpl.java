package service.custom.impl;

import dto.Login;
import entity.LoginEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.LoginDao;
import service.custom.LoginService;
import util.DaoType;

public class LoginServiceImpl implements LoginService {

    private LoginEntity login = null;
    private LoginDao loginDao = DaoFactory.getInstance().getServiceType(DaoType.LOGIN);

    @Override
    public boolean createLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login, LoginEntity.class);
        return loginDao.save(entity);
    }

    @Override
    public boolean verifyLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login,LoginEntity.class);
        LoginEntity search = loginDao.search(login.getEmail());
        return (String.valueOf(entity)).equals(String.valueOf(search));
    }

    @Override
    public boolean validEmail(String email) {
        login = loginDao.search(email);
        if (null != login) {
            return login.getEmail().equals(email);
        }else {
            return false;
        }
    }

    @Override
    public boolean createPassword(String password) {
        System.out.println(this.login);
        loginDao.search(login.getEmail());
        return loginDao.update(new LoginEntity(login.getEmail(),password));
    }
}
