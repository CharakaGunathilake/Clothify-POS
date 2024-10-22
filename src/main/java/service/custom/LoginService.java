package service.custom;

import dto.Login;
import service.SuperService;

public interface LoginService extends SuperService {
    boolean createLogin(Login login);
    boolean validEmail(String email);
    Login searchLogin(String email);
    boolean createPassword(String password);
    void setLoginId();
}
