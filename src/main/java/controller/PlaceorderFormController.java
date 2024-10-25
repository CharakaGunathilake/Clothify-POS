package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CartTM;
import dto.Order;
import dto.OrderDetail;
import dto.Product;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.OrderService;
import service.custom.ProductService;
import util.LoginInfo;
import util.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PlaceorderFormController implements Initializable {

    @FXML
    private Label lblStock;

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
    private Label lblCategory;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPrice;

    @FXML
    private JFXTextField txtQty;

    private int index;
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);
    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        int qty = 0;
        try {
            qty = Integer.parseInt(txtQty.getText());
            if ((qty > Integer.parseInt(lblStock.getText()))) {
                new Alert(Alert.AlertType.WARNING, "Invalid qty").show();
            } else {
                if (cartTMS.contains(cmbItemCode.getValue())) {
                    new Alert(Alert.AlertType.WARNING, "Item already added with the code:" + cmbItemCode.getValue()).show();
                } else {
                    cartTMS.add(new CartTM(
                            lblId.getText(),
                            cmbItemCode.getValue(),
                            lblName.getText(),
                            lblCategory.getText(),
                            qty,
                            Double.parseDouble(lblPrice.getText()),
                            Double.parseDouble(lblPrice.getText()) * qty)
                    );
                    getNetTotal();
                    tblEmployees.setItems(cartTMS);
                    setTextToEmpty();
                }
            }
        } catch (NumberFormatException e) {
            txtQty.setText("1");

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        cartTMS.remove(index);
        getNetTotal();
        setTextToEmpty();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (cartTMS.contains(cmbItemCode.getValue())) {
            cartTMS.set(index, new CartTM(
                    lblId.getText(),
                    cmbItemCode.getValue(),
                    lblName.getText(),
                    lblCategory.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(lblPrice.getText()),
                    Double.parseDouble(lblPrice.getText()) * Integer.parseInt(txtQty.getText())
            ));
            getNetTotal();
            tblEmployees.setItems(cartTMS);
            setTextToEmpty();
        } else {
            new Alert(Alert.AlertType.WARNING, "No item added with the code:" + cmbItemCode.getValue()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblEmpId.setText(LoginInfo.getInstance().getUserId());
        loadItem();
        localDateAndTime();
        lblId.setText(orderService.generateId());
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(((observableValue, o, newValue) -> {
            if (newValue != null) {
                searchItem(newValue);
            }
        }));
        tblEmployees.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->
        {
            if (null != newValue) {
                searchItem(newValue.getItemCode());
                index = cartTMS.indexOf(new CartTM(
                        lblId.getText(),
                        cmbItemCode.getValue(),
                        lblName.getText(),
                        lblCategory.getText(),
                        newValue.getQty(),
                        Double.parseDouble(lblPrice.getText()),
                        (Double.parseDouble(lblPrice.getText()) * newValue.getQty())
                ));
            }
        }));
    }

    private void loadItem() {
        ObservableList<String> itemIdList = productService.getProductIds();
        cmbItemCode.setItems(itemIdList);
    }

    private void searchItem(String itemCode) {
        Product product = productService.searchProduct(itemCode);
        lblName.setText(product.getName());
        lblCategory.setText(product.getCategory());
        lblStock.setText(product.getQty().toString());
        lblPrice.setText(product.getPrice().toString());
    }

    private void getNetTotal() {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS) {
            total += cartTM.getTotal();
        }
        lblTotal.setText(total.toString());
    }

    private void setTextToEmpty() {
        lblName.setText("");
        lblPrice.setText("");
        cmbItemCode.setItems(cmbItemCode.getItems());
        txtQty.setText("");
        lblStock.setText("");
        lblCategory.setText("");
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Place Order");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.OK)) {
            String orderId = lblId.getText();
            LocalDate orderDate = LocalDate.now();
            String orderTime = lblTime.getText();
            String employeeId = lblEmpId.getText();

            Set<OrderDetail> orderDetails = new HashSet<>();
            cartTMS.forEach(obj -> {
                orderDetails.add(new OrderDetail(
                        obj.getOrderId(),
                        obj.getItemCode(),
                        obj.getName(),
                        obj.getQty(),
                        obj.getUnitPrice(),
                        obj.getTotal()
                ));
            });
            Order order = new Order(orderId, orderDate, orderTime, employeeId, Double.parseDouble(lblTotal.getText()), orderDetails);
            if (orderService.addOrder(order)) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully!").show();
                lblId.setText(orderService.generateId());
                cartTMS.removeAll();
                setTextToEmpty();
                tblEmployees.refresh();
                getNetTotal();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to place order!").show();
            }
        } else {
            lblId.setText(orderService.generateId());
            cartTMS.removeAll();
            setTextToEmpty();
            tblEmployees.refresh();
            getNetTotal();
        }
    }
}
