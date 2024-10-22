package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CartTM;
import dto.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceorderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmpId;

    @FXML
    private Label lblId;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<CartTM> tblEmployees;

    @FXML
    private JFXTextField txtCategory;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtQty;

    private int index;
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        if (Integer.parseInt(txtQty.getText()) < Integer.parseInt(txtQty.getText())) {
            new Alert(Alert.AlertType.WARNING, "Invalid qty").show();
        } else {
            cartTMS.add(new CartTM(
                    cmbItemCode.getValue(),
                    txtName.getText(),
                    txtCategory.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtPrice.getText()),
                    Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText())
            ));
            getNetTotal();
            tblEmployees.setItems(cartTMS);
            setTextToEmpty();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        getNetTotal();
        cartTMS.remove(index);
        setTextToEmpty();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        cartTMS.set(index, new CartTM(
                cmbItemCode.getValue(),
                txtName.getText(),
                txtCategory.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtPrice.getText()),
                Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText())
        ));
        getNetTotal();
        tblEmployees.setItems(cartTMS);
        setTextToEmpty();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItem();
        localDateAndTime();

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, newValue) -> {
            if (newValue != null) {
                searchItems(newValue);
            }
        }));
        tblEmployees.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (null != newValue) {
                setTextToValues(newValue);
                index = cartTMS.indexOf(new CartTM(
                        cmbItemCode.getValue(),
                        txtName.getText(),
                        txtCategory.getText(),
                        Integer.parseInt(txtQty.getText()),
                        Double.parseDouble(txtPrice.getText()),
                        Double.parseDouble(txtPrice.getText()) * Integer.parseInt(txtQty.getText())
                ));
            }
        }));
    }

    private void loadItem() {
        ObservableList<String> itemIdList = productService.getProductIds();
        cmbItemCode.setItems(itemIdList);
    }

    private void searchItems(String itemCode) {
        Product product = productService.searchProduct(itemCode);
        txtName.setText(product.getName());
        txtCategory.setText(product.getCategory());
        txtQty.setText(product.getQty().toString());
        txtPrice.setText(product.getPrice().toString());
    }

    private void getNetTotal() {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS) {
            total += cartTM.getTotal();
        }
        lblTotal.setText(total.toString());
    }

    private void setTextToValues(CartTM newValue) {
        cmbItemCode.setValue(newValue.getItemCode());
        txtName.setText(newValue.getName());
        txtCategory.setText(newValue.getCategory());
        txtQty.setText(newValue.getQty().toString());
        txtPrice.setText(newValue.getUnitPrice().toString());
    }

    private void setTextToEmpty() {
        txtName.setText("");
        txtPrice.setText("");
        txtQty.setText("");
        txtCategory.setText("");
    }

    private void localDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);
        lblDate.setText(dateNow);
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent actionEvent) {
    }
}
