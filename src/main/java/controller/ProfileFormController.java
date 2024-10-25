package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.LoginService;
import util.LoginInfo;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFormController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPassword;

    LoginService loginService = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    @FXML
    void btnEditOnAction(ActionEvent event) {
        try {
            Stage stage = (Stage) scenePane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/recovery_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblId.setText(LoginInfo.getInstance().getUserId());
        lblName.setText(LoginInfo.getInstance().getEmail());
        final String password = LoginInfo.getInstance().getPassword();
        lblPassword.setText(password.replaceAll("[A-Z|a-z 0-9]","*"));

    }
}
