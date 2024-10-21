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

public class UserFormController {

    @FXML
    private Label lblTitle;

    @FXML
    private BorderPane borderPane;

    @FXML
    void btnOrderOnAction(ActionEvent event) {
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/placeorder_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Order Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProductsOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/products_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Product Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProfileOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/profile_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Profile Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReportsOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/Report_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Report Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            AnchorPane view = FXMLLoader.load(getClass().getResource("../view/supplier_form.fxml"));
            borderPane.setCenter(view);
            lblTitle.setText("Supplier Management");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
    }
}
