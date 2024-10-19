package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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

public class RecoveryFormController {

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        if (!(txtEmail.getText()).isEmpty()) {
            if (service.validEmail(txtEmail.getText())){
                Stage stage = new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/renew_password_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Invalid or not found recovery E-mail").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING,"PLease Enter Your Recovery E-mail Above").show();
        }
    }

}
