package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuppliersFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colItem;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private JFXTextField txtCompany;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtItem;

    @FXML
    private JFXTextField txtName;

    SupplierService service = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblId.setText(service.generateId());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (null != newValue) {
                setTextToValues(newValue);
            }
        }));
        loadTable();
    }

    @FXML
    void btnAddOnAction() {
        Supplier supplier = new Supplier(
                lblId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                txtItem.getText()
        );
        if (service.addSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier added Successfully!").show();
            lblId.setText(service.generateId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Add Supplier!").show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOnAction() {
        if (service.deleteSupplier(lblId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully").show();
            lblId.setText(service.generateId());
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Delete Supplier!").show();
        }
        loadTable();
    }

    @FXML
    void btnUpdateOnAction() {
        Supplier supplier = new Supplier(
                lblId.getText(),
                txtName.getText(),
                txtCompany.getText(),
                txtEmail.getText(),
                txtItem.getText()
        );
        if (service.updateSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Update Supplier!").show();
        }
        loadTable();
    }

    private void setTextToValues(Supplier newValue) {
        lblId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtCompany.setText(newValue.getCompany());
        txtEmail.setText(newValue.getEmail());
        txtItem.setText(newValue.getItem());
    }

    private void loadTable() {
        ObservableList<Supplier> suppliersList = service.getAll();
        tblSupplier.setItems(suppliersList);
    }

}
