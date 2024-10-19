package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Login;
import service.ServiceFactory;
import service.custom.LoginService;
import util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        if (!hasEmptyFields()) {
            boolean isDone = service.createLogin(new Login(
                    txtEmail.getText(),
                    txtPassword.getText()
                    )
            );
            if (isDone) {
                new Alert(Alert.AlertType.WARNING,"Login created Successfully!!").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Failed to create login!!").show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty Text Fields!").show();
        }
    }

    private boolean hasEmptyFields() {
        return txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty();
    }

}
