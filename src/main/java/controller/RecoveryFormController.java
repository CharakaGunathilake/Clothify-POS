package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.ServiceFactory;
import service.custom.LoginService;
import util.LoginInfo;
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
    private AnchorPane scenePane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTextField txtEmail;

    private Stage stage;
    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        if (!(txtEmail.getText()).isEmpty()) {
            if (service.validEmail(txtEmail.getText())) {
                LoginInfo.getInstance().setEmail(txtEmail.getText());
                stage = (Stage) scenePane.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/renew_password_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Invalid or not found recovery E-mail").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "PLease Enter Your Recovery E-mail").show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent actionEvent) {
        stage = (Stage) scenePane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
