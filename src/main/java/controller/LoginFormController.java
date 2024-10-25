package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.Login;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
import util.LoginInfo;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXPasswordField txtPassword;

    private final LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
    Stage stage;

    @FXML
    void btnLogInOnAction(ActionEvent event) {
        if (!hasEmptyFields()) {
            try {
                Login login = service.searchLogin(txtEmail.getText());
                boolean isValidLogin = verifyLogin(login);
                if (isValidLogin) {
                    LoginInfo info = LoginInfo.getInstance();
                    info.setEmail(login.getEmail());
                    info.setUserId(login.getUserId());
                    info.setPassword(login.getPassword());
                    info.setEmail(txtEmail.getText());
                    stage = (Stage) scenePane.getScene().getWindow();
                    if ((login.getUserId()).contains("A")) {
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin_user_form.fxml"))));
                            stage.show();
                            stage.setResizable(false);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if ((login.getUserId()).contains("E")) {
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user_form.fxml"))));
                            stage.show();
                            stage.setResizable(false);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Incorrect Email or Password");
                    alert.setHeaderText("Login Failed!");
                    alert.show();
                }
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No Login Data Found");
                alert.setHeaderText("Create an account before logging in");
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.OK)) {
                    try {
                        stage = (Stage) scenePane.getScene().getWindow();
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
                        stage.show();
                        stage.setResizable(false);
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill the empty input fields!!").show();
        }
    }

    private boolean verifyLogin(Login login) {
        return login.getPassword().equals(txtPassword.getText()) && login.getEmail().equals(txtEmail.getText());
    }

    private boolean hasEmptyFields() {
        return (txtEmail.getText()).isEmpty() || (txtPassword.getText()).isEmpty();
    }


    @FXML
    void lnkForgotPasswordOnAction(ActionEvent event) {
        try {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/recovery_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lnkCreateAccountOnAction(ActionEvent actionEvent) {
        try {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
