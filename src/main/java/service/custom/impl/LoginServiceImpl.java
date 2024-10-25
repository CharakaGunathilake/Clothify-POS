package service.custom.impl;

import dto.Login;
import entity.EmployeeEntity;
import entity.LoginEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.LoginDao;
import service.custom.LoginService;
import util.DaoType;
import util.LoginInfo;

public class LoginServiceImpl implements LoginService {

    private LoginEntity login = null;
    private final LoginDao loginDao = DaoFactory.getInstance().getServiceType(DaoType.LOGIN);
    private final EmployeeDao employeeDao = DaoFactory.getInstance().getServiceType(DaoType.EMPLOYEE);

    @Override
    public boolean createLogin(Login login) {
        final EmployeeEntity search = employeeDao.search(login.getUserId());
        final String id = search == null ? null : search.getId();
        if (null != id && id.equals(login.getUserId())) {
            LoginEntity entity = new ModelMapper().map(login, LoginEntity.class);
            return loginDao.save(entity);
        }else {
            return false;
        }
    }

    @Override
    public boolean validEmail(String email) {
        this.login = loginDao.search(email);
        if (null != login) {
            return this.login.getEmail().equals(email);
        }else {
            return false;
        }
    }

    @Override
    public Login searchLogin(String email) {
        return new ModelMapper().map(loginDao.search(email),Login.class);
    }

    @Override
    public boolean createPassword(String password) {
        this.login = loginDao.search(LoginInfo.getInstance().getEmail());
        return loginDao.update(new LoginEntity(login.getEmail(),login.getUserId(),password));
    }
}
