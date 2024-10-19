package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Login;
import service.ServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import service.custom.LoginService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbPosition;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        if (!hasEmptyFields()) {
            boolean isValidLogin = service.verifyLogin(
                    new Login(
                        txtEmail.getText(),
                        txtPassword.getText()
            ));
            if (isValidLogin){
                if (cmbPosition.getValue() != null) {
                    if ((cmbPosition.getValue()).equals("Admin")) {
                        Stage stage = new Stage();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_user_form.fxml"))));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if ((cmbPosition.getValue()).equals("Employee")) {
                        Stage stage = new Stage();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_form.fxml"))));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR,"Please select job role!").show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to Login!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Please fill the empty input fields!!").show();
        }
    }

    private boolean hasEmptyFields() {
        return (txtEmail.getText()).isEmpty() || (txtPassword.getText()).isEmpty();
    }


    @FXML
    void lnkForgotPasswordOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/recovery_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lnkCreateAccountOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roles = FXCollections.observableArrayList();
        roles.add("Admin");
        roles.add("Employee");
        cmbPosition.setItems(roles);
    }
}