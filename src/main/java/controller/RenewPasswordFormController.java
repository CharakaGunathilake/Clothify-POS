package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.NonNull;
import service.ServiceFactory;
import service.custom.LoginService;
import util.ServiceType;

import java.io.IOException;
import java.util.Optional;

public class RenewPasswordFormController {

    @FXML
    private AnchorPane scenePane;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtNewPassword;
    private Stage stage;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        stage = (Stage) scenePane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/recovery_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        LoginService service = ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);
       if(!hasEmptyFields()&&isSamePassword()){
           if (service.createPassword(txtConfirmPassword.getText())) {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Login to Continue.");
               alert.setHeaderText("New Password Created");
               Optional<ButtonType> buttonType = alert.showAndWait();
               if (buttonType.get().equals(ButtonType.OK)) {
                   stage = (Stage) scenePane.getScene().getWindow();
                   try {
                       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login_form.fxml"))));
                       stage.show();
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
               }
           }
       }else {
           new Alert(Alert.AlertType.ERROR,"Empty or different Password fields!!").show();
       }
    }

    private boolean isSamePassword() {
        if((txtNewPassword.getText()).equals(txtConfirmPassword.getText())){
            return true;
        }else{
            new Alert(Alert.AlertType.ERROR,"Password fields doesn't match");
            return false;
        }
    }

    private boolean hasEmptyFields() {
        return (txtNewPassword.getText()).isEmpty() || (txtConfirmPassword.getText()).isEmpty();
    }
}
