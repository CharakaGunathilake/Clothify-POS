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

    LoginDao loginDao = DaoFactory.getInstance().getServiceType(DaoType.LOGIN);

    @Override
    public boolean createLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login, LoginEntity.class);
        return loginDao.save(entity);
    }

    @Override
    public boolean verifyLogin(Login login) {
        LoginEntity entity = new ModelMapper().map(login, LoginEntity.class);
        return loginDao.verifyLogin(entity);
    }

    @Override
    public boolean validEmail(String email) {
        return loginDao.validEmail(email);
    }

    @Override
    public boolean createPassword(String password) {
        return false;
    }
}
