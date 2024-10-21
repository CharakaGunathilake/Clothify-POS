package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminUserFormController {

    @FXML
    private Label lblTitle;

    @FXML
    private BorderPane borderPane;

    @FXML
    void btnEmployeesOnAction(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/employees_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Employee Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProductsOnAction(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/products_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Product Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/report_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Reports Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/supplier_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Supplier Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnLogOutOnAction(ActionEvent actionEvent) {

        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
