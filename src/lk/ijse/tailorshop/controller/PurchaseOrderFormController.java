package lk.ijse.tailorshop.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.MeasurementDTO;
import lk.ijse.tailorshop.dto.OrderDTO;
import lk.ijse.tailorshop.dto.OrderDetailsDTO;
import lk.ijse.tailorshop.entity.OrderDetails;
import lk.ijse.tailorshop.service.Custom.CustomerService;
import lk.ijse.tailorshop.service.Custom.OrderService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.view.tm.OrderDetailsTM;
import lk.ijse.tailorshop.view.tm.OrdersTM;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class PurchaseOrderFormController {

    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<String> cmbCustomerNic;

    @FXML
    private ComboBox<String> cmbOrderId;

    @FXML
    private TableColumn<OrdersTM, FontAwesomeIconView> colAction;

    @FXML
    private TableColumn<OrdersTM, Double> colAdvance;

    @FXML
    private TableColumn<OrderDetailsTM, Double> colCost;

    @FXML
    private TableColumn<OrdersTM, String> colDescription;

    @FXML
    private TableColumn<OrderDetailsTM, String> colDressType;

    @FXML
    private TableColumn<OrderDetailsTM, String> colFabricId;

    @FXML
    private TableColumn<OrdersTM, String> colOrderId;

    @FXML
    private TableColumn<OrderDetailsTM, Integer> colQty;

    @FXML
    public TableColumn<OrderDetailsTM, Double> colUnitPrice;

    @FXML
    private TableColumn<OrdersTM, Double> colTotal;

    @FXML
    private TableColumn<OrderDetailsTM, String> colMeasurementId;

    @FXML
    private TableView<OrderDetailsTM> tblOrderDetails;

    @FXML
    private TableView<OrdersTM> tblOrders;

    @FXML
    private TextField txtBust;

    @FXML
    private TextField txtCalf;

    @FXML
    private TextField txtChest;

    @FXML
    private TextField txtChestBoy;

    @FXML
    private TextField txtCrotch;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDownAShoulder;

    @FXML
    private TextField txtDownAShoulderBoy;

    @FXML
    private TextField txtFrockLength;

    @FXML
    private TextField txtFullTotal;

    @FXML
    private TextField txtHip;

    @FXML
    private TextField txtHipAndWest;

    @FXML
    private TextField txtHipFrock;

    @FXML
    private TextField txtInseam;

    @FXML
    private TextField txtKnee;

    @FXML
    private TextField txtLastAmountDue;

    @FXML
    private TextField txtLength;

    @FXML
    private TextField txtNeck;

    @FXML
    private TextField txtNeckBoy;

    @FXML
    private TextField txtOrderDescription;

    @FXML
    private TextField txtShirtLength;

    @FXML
    private TextField txtSkirtLength;

    @FXML
    private TextField txtSleeve;

    @FXML
    private TextField txtSleeveBoy;

    @FXML
    private TextField txtSleeveFrock;

    @FXML
    private TextField txtThigh;

    @FXML
    private TextField txtTotalAdvance;

    @FXML
    private TextField txtUnderBust;

    @FXML
    private TextField txtWest;

    @FXML
    private TextField txtWestFrock;

    @FXML
    private VBox vBoxBlouse;

    @FXML
    private VBox vBoxDenim;

    @FXML
    private VBox vBoxFrockAndSkirt;

    @FXML
    private VBox vBoxShirt;
    private final ObservableList<OrdersTM> ordersTMS = FXCollections.observableArrayList();
    private final ObservableList<OrderDetailsTM> orderDetailsTMS = FXCollections.observableArrayList();
    private final OrderService orderService = ServiceFactory.getInstance().getService(ServiceType.ORDER);
    private final CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    private double totalAdvance;
    private double fullTotal;
    public void initialize(){
        loadCustomerNicCmb();
        setCellFactory();
        reset();
    }

    private void reset() {
        btnConfirm.setDisable(true);
        btnCancel.setDisable(true);
        btnAdd.setDisable(true);
        cmbOrderId.setDisable(true);
        cmbCustomerNic.setDisable(false);
        for (VBox vBox : Arrays.asList(vBoxBlouse, vBoxShirt, vBoxDenim, vBoxFrockAndSkirt)) {
            vBox.setVisible(false);
        }
        ordersTMS.clear();
        tblOrders.refresh();
        orderDetailsTMS.clear();
        tblOrderDetails.refresh();
        txtTotalAdvance.setText(null);
        txtFullTotal.setText(null);
        txtLastAmountDue.setText(null);
    }

    private void setCellFactory() {
        // orders table
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        // order detail table
        colFabricId.setCellValueFactory(new PropertyValueFactory<>("fabricId"));
        colMeasurementId.setCellValueFactory(new PropertyValueFactory<>("measurementId"));
        colDressType.setCellValueFactory(new PropertyValueFactory<>("dressType"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void loadOrderIdCmb(String customerId) {
        cmbOrderId.getItems().clear();
        cmbOrderId.getItems().addAll(orderService.getAllNonReturnOrdersByCustomerId(customerId).stream().map(orderDTO -> orderDTO.getOrderId()).collect(Collectors.toList()));
    }

    private void loadCustomerNicCmb() {
        cmbCustomerNic.getItems().addAll(customerService.getAllCustomer().stream().map(customerDTO -> customerDTO.getNic()).collect(Collectors.toList()));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        btnConfirm.setDisable(false);
        btnCancel.setDisable(false);
        String orderId = cmbOrderId.getValue();
        if (orderId != null){
            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            deleteIcon.setStyle(" -fx-cursor: hand ;" + "-glyph-size:28px;" + "-fx-fill:#ff1744;");
            deleteIcon.setOnMouseClicked((MouseEvent mouseEvent)->{
                OrdersTM ordersTM = tblOrders.getSelectionModel().getSelectedItem();
                if (ordersTM != null){
                    tblOrders.getItems().removeAll(ordersTM);
                    totalAdvance -= ordersTM.getAdvance();
                    fullTotal -= ordersTM.getTotal();
                    setPurchaseDetailField();
                    cmbOrderId.getItems().add(orderId);
                    if (tblOrders.getItems().isEmpty()){
                        cmbCustomerNic.setDisable(false);
                        btnConfirm.setDisable(true);
                        btnCancel.setDisable(true);
                    }
                }
            } );

            OrderDTO orderDTO = orderService.getOrdersByOrderId(orderId);
            OrdersTM ordersTM = new OrdersTM(orderId, orderDTO.getDescription(), orderDTO.getAdvance(), orderDTO.getTotalPrice(), deleteIcon);
            ordersTMS.add(ordersTM);
            totalAdvance += orderDTO.getAdvance();
            fullTotal += orderDTO.getTotalPrice();
            setPurchaseDetailField();
            cmbOrderId.getItems().remove(orderId);
            cmbCustomerNic.setDisable(true);
            tblOrders.setItems(ordersTMS);
            tblOrders.refresh();
        }
    }

    private void setPurchaseDetailField() {
        txtTotalAdvance.setText(NumberFormat.getCurrencyInstance(new Locale("en","in")).format(totalAdvance));
        txtFullTotal.setText(NumberFormat.getCurrencyInstance(new Locale("en","in")).format(fullTotal));
        txtLastAmountDue.setText(NumberFormat.getCurrencyInstance(new Locale("en","in")).format(fullTotal - totalAdvance));
    }

    @FXML
    void btnCancel(ActionEvent event) {
        reset();
        cmbCustomerNic.setValue(null);
        txtCustomerName.setText(null);
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnConfirmOrderOnAction(ActionEvent event) {
        List<String> orderIds = new ArrayList<>();
        for (OrdersTM ordersTM : tblOrders.getItems()){
            orderIds.add(ordersTM.getOrderId());
        }
        if (orderService.purchaseOrders(orderIds)){
            reset();
            cmbCustomerNic.setValue(null);
            txtCustomerName.setText(null);
            new Alert(Alert.AlertType.INFORMATION,"Order purchase successfully !");
        }else {
            new Alert(Alert.AlertType.ERROR,"Order purchase unsuccessfully !");
        }
    }

    @FXML
    void cmbCustomerNicOnAction(ActionEvent event) {
        String customerNic = cmbCustomerNic.getValue();
        if (customerNic != null){
            CustomerDTO customerDTO = customerService.getCustomerByNic(customerNic);
            loadOrderIdCmb(customerDTO.getCustomerId());
            txtCustomerName.setText(customerDTO.getName());
            cmbOrderId.setDisable(false);
        }
    }

    @FXML
    void cmbOrderIdOnAction(ActionEvent event) {
        String orderId = cmbOrderId.getValue();
        if (orderId != null){
            loadOrderDetailTable(orderId);
            txtOrderDescription.setText(orderService.getOrdersByOrderId(orderId).getDescription());
            btnAdd.setDisable(false);
        }else {
            btnAdd.setDisable(true);
            txtOrderDescription.setText(null);
        }

    }

    private void loadOrderDetailTable(String orderId) {
        orderDetailsTMS.clear();
        for (OrderDetailsDTO detailsDTO : orderService.getOrderDetailByOrderId(orderId)){
            MeasurementDTO measurementDTO = orderService.getMeasurementByMeasurementId(detailsDTO.getMeasurementId());
            orderDetailsTMS.add(new OrderDetailsTM(detailsDTO.getFabricId(), detailsDTO.getMeasurementId(), measurementDTO.getDetails(), detailsDTO.getCost(), detailsDTO.getQty(), detailsDTO.getUnitPrice()));
        }
        tblOrderDetails.setItems(orderDetailsTMS);
    }

    @FXML
    void measurementKeyTyped(KeyEvent event) {}

    @FXML
    void tblOrderDetailsClickOnAction(MouseEvent event) {
        OrderDetailsTM orderDetailsTM = tblOrderDetails.getSelectionModel().getSelectedItem();
        if (orderDetailsTM != null){
            for (VBox vBox : Arrays.asList(vBoxBlouse, vBoxShirt, vBoxDenim, vBoxFrockAndSkirt)) {
                vBox.setVisible(false);
            }
            MeasurementDTO measurementDTO = orderService.getMeasurementByMeasurementId(orderDetailsTM.getMeasurementId());
            txtLength.setText(String.valueOf(measurementDTO.getLength()));
            txtNeck.setText(String.valueOf(measurementDTO.getNeck()));
            txtBust.setText(String.valueOf(measurementDTO.getBust()));
            txtDownAShoulder.setText(String.valueOf(measurementDTO.getDownAShoulder()));
            txtUnderBust.setText(String.valueOf(measurementDTO.getUnderBust()));
            txtSleeve.setText(String.valueOf(measurementDTO.getSleeve()));
            txtWest.setText(String.valueOf(measurementDTO.getWest()));
            txtChest.setText(String.valueOf(measurementDTO.getChest()));
            txtShirtLength.setText(String.valueOf(measurementDTO.getLength()));
            txtChestBoy.setText(String.valueOf(measurementDTO.getChest()));
            txtDownAShoulderBoy.setText(String.valueOf(measurementDTO.getDownAShoulder()));
            txtNeckBoy.setText(String.valueOf(measurementDTO.getNeck()));
            txtSleeveBoy.setText(String.valueOf(measurementDTO.getSleeve()));
            txtCrotch.setText(String.valueOf(measurementDTO.getCrotch()));
            txtKnee.setText(String.valueOf(measurementDTO.getKnee()));
            txtCalf.setText(String.valueOf(measurementDTO.getCalf()));
            txtHipAndWest.setText(String.valueOf(measurementDTO.getHipAndWestDistance()));
            txtInseam.setText(String.valueOf(measurementDTO.getInseam()));
            txtThigh.setText(String.valueOf(measurementDTO.getThigh()));
            txtHip.setText(String.valueOf(measurementDTO.getHip()));
            txtWestFrock.setText(String.valueOf(measurementDTO.getWest()));
            txtHipFrock.setText(String.valueOf(measurementDTO.getHip()));
            txtSkirtLength.setText(String.valueOf(measurementDTO.getSkirtLength()));
            txtFrockLength.setText(String.valueOf(measurementDTO.getFrockLength()));
            txtSleeveFrock.setText(String.valueOf(measurementDTO.getSleeve()));
            switch (measurementDTO.getDetails()){
                case "BLOUSE":
                    vBoxBlouse.setVisible(true);
                    break;
                case "SHIRT":
                    vBoxShirt.setVisible(true);
                    break;
                case "DENIM":
                    vBoxDenim.setVisible(true);
                    break;
                case "FROCK/SKIRT":
                    vBoxFrockAndSkirt.setVisible(true);
                    break;
            }
        }
    }

    @FXML
    void tblOrdersClickOnAction(MouseEvent event) {
        OrdersTM ordersTM = tblOrders.getSelectionModel().getSelectedItem();
        if (ordersTM != null){
            loadOrderDetailTable(ordersTM.getOrderId());
        }
    }
}
