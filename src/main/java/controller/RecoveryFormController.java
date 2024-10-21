package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    private AnchorPane scenePane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        if (!(txtEmail.getText()).isEmpty()) {
            if (service.validEmail(txtEmail.getText())) {
                try {
                    AnchorPane view = FXMLLoader.load(getClass().getResource("../view/renew_password_form.fxml"));
                    borderPane.setCenter(view);
                    borderPane.toFront();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Invalid or not found recovery E-mail").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "PLease Enter Your Recovery E-mail Above").show();
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent actionEvent) {
        try {
            scenePane = FXMLLoader.load(getClass().getResource("../view/login_form.fxml"));
            borderPane.setCenter(scenePane);
            borderPane.toFront();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//         Stage stage = (Stage) scenePane.getScene().getWindow();
//        stage.close();
    }
}
