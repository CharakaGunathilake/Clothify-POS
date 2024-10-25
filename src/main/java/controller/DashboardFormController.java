package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.Login;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
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
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardFormController {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtUserId;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
        if (!hasEmptyFields()) {
            if (hasValidFormats()) {
                boolean isDone = service.createLogin(new Login(
                        txtUserId.getText(),
                        txtEmail.getText(),
                        txtPassword.getText()
                ));
                if (isDone) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Login created Successfully!!");
                    setTextToEmpty();
                    btnLogin.setVisible(true);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get().equals(ButtonType.OK)) {
                        Stage stage = (Stage) scenePane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid or duplicate Employee Credentials!!");
                    alert.setHeaderText("Cannot create login!");
                    alert.show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Text Fields!").show();
        }
    }

    private boolean hasValidFormats() {
        Pattern patternPassword = Pattern.compile("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=!])(?=\\S+$).{8,}$");
        Matcher matchedPassword = patternPassword.matcher(txtPassword.getText());
        Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matchedEmail = patternEmail.matcher(txtEmail.getText());
        if (!matchedEmail.matches()) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email format").show();
        } else if (!matchedPassword.matches()) {
            new Alert(Alert.AlertType.ERROR, "Password to weak").show();
        } else {
            return true;
        }
        return false;
    }

    private boolean hasEmptyFields() {
        return txtUserId.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty();
    }

    private void setTextToEmpty() {
        txtUserId.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }

    @FXML
    private void btnLoginOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) scenePane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lnkLoginOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) scenePane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
