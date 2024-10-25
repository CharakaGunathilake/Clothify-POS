package util;

import lombok.Data;

@Data
public class LoginInfo {
    private static LoginInfo instance;
    private String userId;
    private String email;
    private String password;

    private LoginInfo(){}
    public static LoginInfo getInstance() {
        return instance == null ? instance = new LoginInfo() : instance;
    }

}
