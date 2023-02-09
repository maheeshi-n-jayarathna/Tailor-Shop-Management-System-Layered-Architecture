package lk.ijse.tailorshop.controller;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lk.ijse.tailorshop.dto.*;
import lk.ijse.tailorshop.service.Custom.CustomerService;
import lk.ijse.tailorshop.service.Custom.FabricService;
import lk.ijse.tailorshop.service.Custom.OrderService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.view.tm.GetOrderTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GetOrderFormController {

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
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCustomerNic;

    @FXML
    private ComboBox<String> cmbDressType;

    @FXML
    private ComboBox<String> cmbFabricId;

    @FXML
    private TableColumn<GetOrderTM, Double> colCost;

    @FXML
    private TableColumn<GetOrderTM, String> colFabDescription;

    @FXML
    private TableColumn<GetOrderTM, String> colFabId;

    @FXML
    private TableColumn<GetOrderTM, Double> colFullTotal;

    @FXML
    private TableColumn<GetOrderTM, String> colMesId;

    @FXML
    private TableColumn<GetOrderTM, Integer> colQty;

    @FXML
    private TableColumn<GetOrderTM, String> colReceiveDate;

    @FXML
    private TableColumn<GetOrderTM, String> colReturnDate;

    @FXML
    private TableColumn<GetOrderTM, Double> colUnitPrice;

    @FXML
    private DatePicker dpReceiveDate;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private Label lblMeasurementId;

    @FXML
    private TableView<GetOrderTM> tblOrder;

    @FXML
    private TextField txtAdvance;

    @FXML
    private TextField txtBust;

    @FXML
    private TextField txtCalf;

    @FXML
    private TextField txtChest;

    @FXML
    private TextField txtChestBoy;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtCrotch;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDownAShoulder;

    @FXML
    private TextField txtDownAShoulderBoy;

    @FXML
    private TextField txtFrockLength;

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
    private TextField txtLength;

    @FXML
    private TextField txtNeck;

    @FXML
    private TextField txtNeckBoy;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtQtyOnStock;

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
    private TextField txtTotalAmount;

    @FXML
    private TextField txtTotalDescription;

    @FXML
    private TextField txtUnderBust;

    @FXML
    private TextField txtUnitPrice;

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
    private double netTotal;
    private final ObservableList<GetOrderTM> getOrderTms= FXCollections.observableArrayList();
    private final OrderService orderService = ServiceFactory.getInstance().getService(ServiceType.ORDER);
    private final FabricService fabricService = ServiceFactory.getInstance().getService(ServiceType.FABRIC);
    private final CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    private final List<MeasurementDTO> measurements = new ArrayList<>();
    private boolean isUpdate;
    private Pattern measPattern;
    private Pattern pricePattern;

    public void initialize(){
        loadOrderId();
        loadComboBox();
        loadRegex();
        setCellFactory();
        txtTotalAmount.setText(String.valueOf(netTotal));
        lblMeasurementId.setText(orderService.getNextMeasurementId());
        reset();
    }

    private void loadRegex() {
        measPattern =Pattern.compile("^(\\d+)||((\\d+\\.)(\\d+))$");
        pricePattern =Pattern.compile("^(\\d+)||((\\d+\\.)(\\d){2})$");
    }

    private void reset() {
        for (VBox vBox : Arrays.asList(vBoxBlouse, vBoxShirt, vBoxDenim, vBoxFrockAndSkirt)) {
            vBox.setVisible(false);
        }
        lblMeasurementId.setText(getNextMeasId());
        dpReceiveDate.setValue(null);
        dpReturnDate.setValue(null);
        cmbDressType.setValue(null);
        cmbFabricId.setValue(null);
        txtDescription.setText(null);
        txtQtyOnStock.setText(null);
        txtUnitPrice.setText(null);
        txtCost.setText(null);
        txtQty.setText("1");
        btnAdd.setVisible(true);
        btnUpdate.setVisible(false);
        isUpdate=false;
        clearMeasurement();
    }

    private String getNextMeasId() {
        String currentMeasId=lblMeasurementId.getText();
        if ((!measurements.isEmpty()) && measurements.get(measurements.size()-1).getMeasurementId().equals(currentMeasId)){
            return String.format("M"+"%03d",Integer.parseInt(currentMeasId.split("M")[1])+1);
        }
        return currentMeasId;
    }

    private void setCellFactory() {
        colMesId.setCellValueFactory(new PropertyValueFactory<>("measurementId"));
        colReceiveDate.setCellValueFactory(new PropertyValueFactory<>("receiveDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        colFabId.setCellValueFactory(new PropertyValueFactory<>("fabricId"));
        colFabDescription.setCellValueFactory(new PropertyValueFactory<>("fabDescription"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colFullTotal.setCellValueFactory(new PropertyValueFactory<>("fullTotal"));
    }
    private void clearMeasurement(){
        for (TextField textField : Arrays.asList(
                txtLength,
                txtNeck,
                txtBust,
                txtDownAShoulder,
                txtUnderBust,
                txtSleeve,
                txtWest,
                txtChest,

                txtShirtLength,
                txtChestBoy,
                txtDownAShoulderBoy,
                txtNeckBoy,
                txtSleeveBoy,

                txtCrotch,
                txtKnee,
                txtCalf,
                txtHipAndWest,
                txtInseam,
                txtThigh,
                txtHip,

                txtWestFrock,
                txtHipFrock,
                txtSkirtLength,
                txtFrockLength,
                txtSleeveFrock
        )) {
            textField.setText(null);
            textField.setStyle("-fx-border-color: black");
        }
    }
    private void loadOrderId() {
        txtOrderId.setText(orderService.getNextOrderId());
    }
    private void loadComboBox() {
        loadCustomerNic();
        loadDressType();
        loadFabricId();
    }
    private void loadFabricId() {
        for (FabricDTO fabricDTO : fabricService.getAllFabric()){
            cmbFabricId.getItems().add(fabricDTO.getFabricId());
        }
       // cmbFabricId.getItems().addAll(fabricService.getAllFabric().stream().map(f -> f.getFabricId()).collect(Collectors.toList()));
    }

    private void loadDressType() {
        String[] types={"BLOUSE","SHIRT","DENIM","FROCK/SKIRT"};
        cmbDressType.getItems().addAll(types);
    }

    private void loadCustomerNic() {
        for (CustomerDTO customerDTO : customerService.getAllCustomer()) {
            cmbCustomerNic.getItems().add(customerDTO.getNic());
        }
       // cmbCustomerNic.getItems().addAll(customerService.getAllCustomer().stream().map(c -> c.getNic()).collect(Collectors.toList()));
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        boolean state=true;
        if (dpReceiveDate.getValue()==null) {
            dpReceiveDate.setStyle("-fx-border-color: red");
            state=false;
        }if (dpReturnDate.getValue()==null) {
            dpReturnDate.setStyle("-fx-border-color: red");
            state=false;
        }if (cmbDressType.getValue()==null) {
            cmbDressType.setStyle("-fx-border-color: red");
            state=false;
        }else {
            String type=cmbDressType.getValue();
            switch (type){
                case "BLOUSE":
                    if (txtLength.getText()==null || txtLength.getText().isEmpty() || (!measPattern.matcher(txtLength.getText()).matches())){
                        txtLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtNeck.getText()==null || txtNeck.getText().isEmpty() || (!measPattern.matcher(txtNeck.getText()).matches())){
                        txtNeck.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtBust.getText()==null || txtBust.getText().isEmpty() || (!measPattern.matcher(txtBust.getText()).matches())){
                        txtBust.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtDownAShoulder.getText()==null || txtDownAShoulder.getText().isEmpty() || (!measPattern.matcher(txtDownAShoulder.getText()).matches())){
                        txtDownAShoulder.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtUnderBust.getText()==null || txtUnderBust.getText().isEmpty() || (!measPattern.matcher(txtUnderBust.getText()).matches())){
                        txtUnderBust.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtSleeve.getText()==null || txtSleeve.getText().isEmpty() || (!measPattern.matcher(txtSleeve.getText()).matches())){
                        txtSleeve.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtWest.getText()==null || txtWest.getText().isEmpty() || (!measPattern.matcher(txtWest.getText()).matches())){
                        txtWest.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtChest.getText()==null || txtChest.getText().isEmpty() || (!measPattern.matcher(txtChest.getText()).matches())){
                        txtChest.setStyle("-fx-border-color: red");
                        state=false;
                    }
                    break;
                case "SHIRT":
                    if (txtShirtLength.getText()==null || txtShirtLength.getText().isEmpty() || (!measPattern.matcher(txtShirtLength.getText()).matches())){
                        txtShirtLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtChestBoy.getText()==null || txtChestBoy.getText().isEmpty() || (!measPattern.matcher(txtChestBoy.getText()).matches())){
                        txtChestBoy.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtDownAShoulderBoy.getText()==null || txtDownAShoulderBoy.getText().isEmpty() || (!measPattern.matcher(txtDownAShoulderBoy.getText()).matches())){
                        txtDownAShoulderBoy.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtNeckBoy.getText()==null || txtNeckBoy.getText().isEmpty() || (!measPattern.matcher(txtNeckBoy.getText()).matches())){
                        txtNeckBoy.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtSleeveBoy.getText()==null || txtSleeveBoy.getText().isEmpty() || (!measPattern.matcher(txtSleeveBoy.getText()).matches())){
                        txtSleeveBoy.setStyle("-fx-border-color: red");
                        state=false;
                    }
                    break;
                case "DENIM":
                    if (txtCrotch.getText()==null || txtCrotch.getText().isEmpty() || (!measPattern.matcher(txtCrotch.getText()).matches())){
                        txtCrotch.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtKnee.getText()==null || txtKnee.getText().isEmpty() || (!measPattern.matcher(txtKnee.getText()).matches())){
                        txtKnee.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtCalf.getText()==null || txtCalf.getText().isEmpty() || (!measPattern.matcher(txtCalf.getText()).matches())){
                        txtCalf.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtHipAndWest.getText()==null || txtHipAndWest.getText().isEmpty() || (!measPattern.matcher(txtHipAndWest.getText()).matches())){
                        txtHipAndWest.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtInseam.getText()==null || txtInseam.getText().isEmpty() || (!measPattern.matcher(txtInseam.getText()).matches())){
                        txtInseam.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtThigh.getText()==null || txtThigh.getText().isEmpty() || (!measPattern.matcher(txtThigh.getText()).matches())){
                        txtThigh.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtHip.getText()==null || txtHip.getText().isEmpty() || (!measPattern.matcher(txtHip.getText()).matches())){
                        txtHip.setStyle("-fx-border-color: red");
                        state=false;
                    }
                    break;
                case "FROCK/SKIRT":
                    if (txtWestFrock.getText()==null || txtWestFrock.getText().isEmpty() || (!measPattern.matcher(txtWestFrock.getText()).matches())){
                        txtWestFrock.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtHipFrock.getText()==null || txtHipFrock.getText().isEmpty() || (!measPattern.matcher(txtHipFrock.getText()).matches())){
                        txtHipFrock.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtSkirtLength.getText()==null || txtSkirtLength.getText().isEmpty() || (!measPattern.matcher(txtSkirtLength.getText()).matches())){
                        txtSkirtLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtFrockLength.getText()==null || txtFrockLength.getText().isEmpty() || (!measPattern.matcher(txtFrockLength.getText()).matches())){
                        txtFrockLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtSleeveFrock.getText()==null || txtSleeveFrock.getText().isEmpty() || (!measPattern.matcher(txtSleeveFrock.getText()).matches())){
                        txtSleeveFrock.setStyle("-fx-border-color: red");
                        state=false;
                    }
                    break;
            }
        }if (cmbFabricId.getValue()==null){
            cmbFabricId.setStyle("-fx-border-color: red");
            state=false;
        }if (txtCost.getText()==null || txtCost.getText().isEmpty() || (!pricePattern.matcher(txtCost.getText()).matches())){
            txtCost.setStyle("-fx-border-color: red");
            state=false;
        }if (txtQty.getText()==null || txtQty.getText().isEmpty()){
            txtQty.setStyle("-fx-border-color: red");
            state=false;
        }
        if (state) {
            String type=cmbDressType.getValue();
            String measId = lblMeasurementId.getText();
            String receiveDate = String.valueOf(dpReceiveDate.getValue());
            String returnDate = String.valueOf(dpReturnDate.getValue());
            double cost = Double.parseDouble(txtCost.getText());
            String fabricId = cmbFabricId.getValue();
            String fabDesc = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            int qtyOnStock=Integer.parseInt(txtQtyOnStock.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double fullTotal = qty * unitPrice + cost;

            if (qty>qtyOnStock){ //qty not available
                txtQty.setStyle("-fx-border-color: red");
                return;
            }
            //measurement
            double length=0;
            double neck=0;
            double bust=0;
            double downAShoulder=0;
            double underBust=0;
            double sleeve=0;
            double west=0;
            double chest=0;
            double crotch=0;
            double knee=0;
            double calf=0;
            double hipAndWestDistance=0;
            double inseam=0;
            double thigh=0;
            double hip=0;
            double skirtLength=0;
            double frockLength=0;
            switch (type){
                case "BLOUSE":
                    length=Double.parseDouble(txtLength.getText());
                    neck=Double.parseDouble(txtNeck.getText());
                    bust=Double.parseDouble(txtBust.getText());
                    downAShoulder=Double.parseDouble(txtDownAShoulder.getText());
                    underBust=Double.parseDouble(txtUnderBust.getText());
                    sleeve=Double.parseDouble(txtSleeve.getText());
                    west=Double.parseDouble(txtWest.getText());
                    chest=Double.parseDouble(txtChest.getText());
                    break;
                case "SHIRT":
                    length=Double.parseDouble(txtShirtLength.getText());
                    chest=Double.parseDouble(txtChestBoy.getText());
                    downAShoulder=Double.parseDouble(txtDownAShoulderBoy.getText());
                    neck=Double.parseDouble(txtNeckBoy.getText());
                    sleeve=Double.parseDouble(txtSleeveBoy.getText());
                    break;
                case "DENIM":
                    crotch=Double.parseDouble(txtCrotch.getText());
                    knee=Double.parseDouble(txtKnee.getText());
                    calf=Double.parseDouble(txtCalf.getText());
                    hipAndWestDistance=Double.parseDouble(txtHipAndWest.getText());
                    inseam=Double.parseDouble(txtInseam.getText());
                    thigh=Double.parseDouble(txtThigh.getText());
                    hip=Double.parseDouble(txtHip.getText());
                    break;
                case "FROCK/SKIRT":
                    west=Double.parseDouble(txtWestFrock.getText());
                    hip=Double.parseDouble(txtHipFrock.getText());
                    skirtLength=Double.parseDouble(txtSkirtLength.getText());
                    frockLength=Double.parseDouble(txtFrockLength.getText());
                    sleeve=Double.parseDouble(txtSleeveFrock.getText());
                    break;
            }
            measurements.add(new MeasurementDTO(
                    measId,
                    type,
                    length,
                    neck,
                    bust,
                    downAShoulder,
                    underBust,
                    sleeve,
                    west,
                    chest,
                    crotch,
                    knee,
                    calf,
                    hipAndWestDistance,
                    inseam,
                    thigh,
                    hip,
                    skirtLength,
                    frockLength
            ));

            netTotal += fullTotal;
            txtTotalAmount.setText(String.valueOf(netTotal));
            getOrderTms.add(new GetOrderTM(measId, receiveDate, returnDate, cost, fabricId, fabDesc, qty, unitPrice, fullTotal));
            tblOrder.setItems(getOrderTms);
            reset();
        }
    }

    @FXML
    void btnCancel(ActionEvent event) {
        loadOrderId();
        cmbCustomerNic.setValue(null);
        txtCustomerName.setText(null);
        reset();
        txtTotalAmount.setText(null);
        txtAdvance.setText(null);
        txtTotalDescription.setText(null);
        getOrderTms.clear();
        tblOrder.refresh();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnConfirmOrderOnAction(ActionEvent event) {
        boolean state=true;
        if (tblOrder.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cart Empty");
            alert.setContentText("please enter the data into the table");
            alert.show();
            state=false;
        }if (cmbCustomerNic.getValue()==null){
            cmbCustomerNic.setStyle("-fx-border-color: red");
            state=false;
        }if (txtAdvance.getText()==null || txtAdvance.getText().isEmpty() || (!pricePattern.matcher(txtAdvance.getText()).matches())){
            txtAdvance.setStyle("-fx-border-color: red");
            state=false;
        }
        if (state){
            String orderId = txtOrderId.getText();
            String cusId = customerService.getCustomerByNic(cmbCustomerNic.getValue()).getCustomerId();
            String desc=txtTotalDescription.getText();
            double advance=Double.parseDouble(txtAdvance.getText());
            //net total
            List<OrderDetailsDTO> cart = new ArrayList<>();
            for (int i = 0; i < tblOrder.getItems().size(); i++){
                GetOrderTM tm=getOrderTms.get(i);
                cart.add(new OrderDetailsDTO(
                        orderId,
                        tm.getFabricId(),
                        tm.getMeasurementId(),
                        tm.getQty(),
                        tm.getUnitPrice(),
                        LocalDate.parse(tm.getReceiveDate()),
                        LocalDate.parse(tm.getReturnDate()),
                        tm.getCost(),
                        null
                ));
            }
            OrderDTO orderDTO=new OrderDTO(orderId,cusId,desc,advance,netTotal,"preparing",cart,measurements);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure ? ");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.OK) {
                if (orderService.saveOrder(orderDTO)) {
                    new Alert(Alert.AlertType.INFORMATION,"Order added !").show();
                    btnCancel(event);
                } else {
                    new Alert(Alert.AlertType.INFORMATION,"Order adding fail !").show();
                }
            }
        }
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        if (tblOrder.getItems().isEmpty() || tblOrder.getSelectionModel().isEmpty())
            return;
        GetOrderTM tm=tblOrder.getSelectionModel().getSelectedItem();
        tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItem());
        netTotal-=tm.getFullTotal();
        txtTotalAmount.setText(String.valueOf(netTotal));
        int index=searchIndex(tm.getMeasurementId());
        if (index != -1){
            measurements.remove(index);
        }
        reset();
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        reset();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean state=true;
        if (dpReceiveDate.getValue()==null) {
            dpReceiveDate.setStyle("-fx-border-color: red");
            state=false;
        }if (dpReturnDate.getValue()==null) {
            dpReturnDate.setStyle("-fx-border-color: red");
            state=false;
        }if (cmbDressType.getValue()==null) {
            cmbDressType.setStyle("-fx-border-color: red");
            state=false;
        }else {
            String type=cmbDressType.getValue();
            switch (type){
                case "BLOUSE":
                    if (txtLength.getText()==null || txtLength.getText().isEmpty() || (!measPattern.matcher(txtLength.getText()).matches())){
                        txtLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtNeck.getText()==null || txtNeck.getText().isEmpty() || (!measPattern.matcher(txtNeck.getText()).matches())){
                    txtNeck.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtBust.getText()==null || txtBust.getText().isEmpty() || (!measPattern.matcher(txtBust.getText()).matches())){
                    txtBust.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtDownAShoulder.getText()==null || txtDownAShoulder.getText().isEmpty() || (!measPattern.matcher(txtDownAShoulder.getText()).matches())){
                    txtDownAShoulder.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtUnderBust.getText()==null || txtUnderBust.getText().isEmpty() || (!measPattern.matcher(txtUnderBust.getText()).matches())){
                    txtUnderBust.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtSleeve.getText()==null || txtSleeve.getText().isEmpty() || (!measPattern.matcher(txtSleeve.getText()).matches())){
                    txtSleeve.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtWest.getText()==null || txtWest.getText().isEmpty() || (!measPattern.matcher(txtWest.getText()).matches())){
                    txtWest.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtChest.getText()==null || txtChest.getText().isEmpty() || (!measPattern.matcher(txtChest.getText()).matches())){
                    txtChest.setStyle("-fx-border-color: red");
                    state=false;
                }
                    break;
                case "SHIRT":
                    if (txtShirtLength.getText()==null || txtShirtLength.getText().isEmpty() || (!measPattern.matcher(txtShirtLength.getText()).matches())){
                        txtShirtLength.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtChestBoy.getText()==null || txtChestBoy.getText().isEmpty() || (!measPattern.matcher(txtChestBoy.getText()).matches())){
                    txtChestBoy.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtDownAShoulderBoy.getText()==null || txtDownAShoulderBoy.getText().isEmpty() || (!measPattern.matcher(txtDownAShoulderBoy.getText()).matches())){
                    txtDownAShoulderBoy.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtNeckBoy.getText()==null || txtNeckBoy.getText().isEmpty() || (!measPattern.matcher(txtNeckBoy.getText()).matches())){
                    txtNeckBoy.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtSleeveBoy.getText()==null || txtSleeveBoy.getText().isEmpty() || (!measPattern.matcher(txtSleeveBoy.getText()).matches())){
                    txtSleeveBoy.setStyle("-fx-border-color: red");
                    state=false;
                }
                    break;
                case "DENIM":
                    if (txtCrotch.getText()==null || txtCrotch.getText().isEmpty() || (!measPattern.matcher(txtCrotch.getText()).matches())){
                        txtCrotch.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtKnee.getText()==null || txtKnee.getText().isEmpty() || (!measPattern.matcher(txtKnee.getText()).matches())){
                    txtKnee.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtCalf.getText()==null || txtCalf.getText().isEmpty() || (!measPattern.matcher(txtCalf.getText()).matches())){
                    txtCalf.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtHipAndWest.getText()==null || txtHipAndWest.getText().isEmpty() || (!measPattern.matcher(txtHipAndWest.getText()).matches())){
                    txtHipAndWest.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtInseam.getText()==null || txtInseam.getText().isEmpty() || (!measPattern.matcher(txtInseam.getText()).matches())){
                    txtInseam.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtThigh.getText()==null || txtThigh.getText().isEmpty() || (!measPattern.matcher(txtThigh.getText()).matches())){
                    txtThigh.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtHip.getText()==null || txtHip.getText().isEmpty() || (!measPattern.matcher(txtHip.getText()).matches())){
                    txtHip.setStyle("-fx-border-color: red");
                    state=false;
                }
                    break;
                case "FROCK/SKIRT":
                    if (txtWestFrock.getText()==null || txtWestFrock.getText().isEmpty() || (!measPattern.matcher(txtWestFrock.getText()).matches())){
                        txtWestFrock.setStyle("-fx-border-color: red");
                        state=false;
                    }if (txtHipFrock.getText()==null || txtHipFrock.getText().isEmpty() || (!measPattern.matcher(txtHipFrock.getText()).matches())){
                    txtHipFrock.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtSkirtLength.getText()==null || txtSkirtLength.getText().isEmpty() || (!measPattern.matcher(txtSkirtLength.getText()).matches())){
                    txtSkirtLength.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtFrockLength.getText()==null || txtFrockLength.getText().isEmpty() || (!measPattern.matcher(txtFrockLength.getText()).matches())){
                    txtFrockLength.setStyle("-fx-border-color: red");
                    state=false;
                }if (txtSleeveFrock.getText()==null || txtSleeveFrock.getText().isEmpty() || (!measPattern.matcher(txtSleeveFrock.getText()).matches())){
                    txtSleeveFrock.setStyle("-fx-border-color: red");
                    state=false;
                }
                    break;
            }
        }if (cmbFabricId.getValue()==null){
            cmbFabricId.setStyle("-fx-border-color: red");
            state=false;
        }if (txtCost.getText()==null || txtCost.getText().isEmpty() || (!pricePattern.matcher(txtCost.getText()).matches())){
            txtCost.setStyle("-fx-border-color: red");
            state=false;
        }if (txtQty.getText()==null || txtQty.getText().isEmpty()){
            txtQty.setStyle("-fx-border-color: red");
            state=false;
        }
        if (state) {
            String type = cmbDressType.getValue();
            String measId = lblMeasurementId.getText();
            String receiveDate = String.valueOf(dpReceiveDate.getValue());
            String returnDate = String.valueOf(dpReturnDate.getValue());
            double cost = Double.parseDouble(txtCost.getText());
            String fabricId = cmbFabricId.getValue();
            String fabDesc = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            int qtyOnStock=Integer.parseInt(txtQtyOnStock.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            double fullTotal = (qty * unitPrice) + cost;
            if (qty>qtyOnStock){ //qty not available
                txtQty.setStyle("-fx-border-color: red");
                return;
            }
            //measurement
            double length = 0;
            double neck = 0;
            double bust = 0;
            double downAShoulder = 0;
            double underBust = 0;
            double sleeve = 0;
            double west = 0;
            double chest = 0;
            double crotch = 0;
            double knee = 0;
            double calf = 0;
            double hipAndWestDistance = 0;
            double inseam = 0;
            double thigh = 0;
            double hip = 0;
            double skirtLength = 0;
            double frockLength = 0;
            switch (type) {
                case "BLOUSE":
                    length = Double.parseDouble(txtLength.getText());
                    neck = Double.parseDouble(txtNeck.getText());
                    bust = Double.parseDouble(txtBust.getText());
                    downAShoulder = Double.parseDouble(txtDownAShoulder.getText());
                    underBust = Double.parseDouble(txtUnderBust.getText());
                    sleeve = Double.parseDouble(txtSleeve.getText());
                    west = Double.parseDouble(txtWest.getText());
                    chest = Double.parseDouble(txtChest.getText());
                    break;
                case "SHIRT":
                    length = Double.parseDouble(txtShirtLength.getText());
                    chest = Double.parseDouble(txtChestBoy.getText());
                    downAShoulder = Double.parseDouble(txtDownAShoulderBoy.getText());
                    neck = Double.parseDouble(txtNeckBoy.getText());
                    sleeve = Double.parseDouble(txtSleeveBoy.getText());
                    break;
                case "DENIM":
                    crotch = Double.parseDouble(txtCrotch.getText());
                    knee = Double.parseDouble(txtKnee.getText());
                    calf = Double.parseDouble(txtCalf.getText());
                    hipAndWestDistance = Double.parseDouble(txtHipAndWest.getText());
                    inseam = Double.parseDouble(txtInseam.getText());
                    thigh = Double.parseDouble(txtThigh.getText());
                    hip = Double.parseDouble(txtHip.getText());
                    break;
                case "FROCK/SKIRT":
                    west = Double.parseDouble(txtWestFrock.getText());
                    hip = Double.parseDouble(txtHipFrock.getText());
                    skirtLength = Double.parseDouble(txtSkirtLength.getText());
                    frockLength = Double.parseDouble(txtFrockLength.getText());
                    sleeve = Double.parseDouble(txtSleeveFrock.getText());
                    break;
            }
            int index=searchIndex(measId);
            if (index != -1) {
                //table update
                GetOrderTM tm=tblOrder.getSelectionModel().getSelectedItem();
                netTotal-=tm.getFullTotal();
                netTotal += fullTotal;
                txtTotalAmount.setText(String.valueOf(netTotal));
                tm.setReceiveDate(receiveDate);
                tm.setReturnDate(returnDate);
                tm.setCost(cost);
                tm.setFabricId(fabricId);
                tm.setFabDescription(fabDesc);
                tm.setQty(qty);
                tm.setUnitPrice(unitPrice);
                tm.setFullTotal(fullTotal);
                tblOrder.refresh();

                //measurement update
                MeasurementDTO meas=measurements.get(index);
                meas.setDetails(type);
                meas.setLength(length);
                meas.setNeck(neck);
                meas.setBust(bust);
                meas.setDownAShoulder(downAShoulder);
                meas.setUnderBust(underBust);
                meas.setSleeve(sleeve);
                meas.setWest(west);
                meas.setChest(chest);
                meas.setCrotch(crotch);
                meas.setKnee(knee);
                meas.setCalf(calf);
                meas.setHipAndWestDistance(hipAndWestDistance);
                meas.setInseam(inseam);
                meas.setThigh(thigh);
                meas.setHip(hip);
                meas.setSkirtLength(skirtLength);
                meas.setFrockLength(frockLength);

                reset();
            }
        }
    }

    @FXML
    void cmbCustomerNicOnAction(ActionEvent event) {
        cmbCustomerNic.setStyle("-fx-border-color: black");
        String cusNic=cmbCustomerNic.getValue();
        if (cusNic != null) {
            txtCustomerName.setText(customerService.getCustomerByNic(cusNic).getName());
        }
    }

    @FXML
    void cmbDressTypeOnAction(ActionEvent event) {
        cmbDressType.setStyle("-fx-border-color: black");
        clearMeasurement();
        for (VBox vBox:Arrays.asList(vBoxBlouse,vBoxDenim,vBoxShirt,vBoxFrockAndSkirt))
            vBox.setVisible(false);
        String type=cmbDressType.getValue();
        if (type==null)return;
        switch (type){
            case "BLOUSE":
                vBoxBlouse.setVisible(true);
                txtLength.requestFocus();
                break;
            case "SHIRT":
                vBoxShirt.setVisible(true);
                txtShirtLength.requestFocus();
                break;
            case "DENIM":
                vBoxDenim.setVisible(true);
                txtCrotch.requestFocus();
                break;
            case "FROCK/SKIRT":
                vBoxFrockAndSkirt.setVisible(true);
                txtWest.requestFocus();
                break;
        }
    }

    @FXML
    void cmbFabricIdOnAction(ActionEvent event) {
        cmbFabricId.setStyle("-fx-border-color: black");
        String fabId=cmbFabricId.getValue();
        if (fabId != null){
            FabricDTO fabric = fabricService.getFabricByFabricId(fabId);
            txtDescription.setText(fabric.getDescription());
            int qtyOnStock = fabric.getQtyOnStock();
            if (!isUpdate){
                if (!tblOrder.getItems().isEmpty()){
                    for (int i=0;i<tblOrder.getItems().size();i++){
                        if (colFabId.getCellData(i).equals(fabId)){
                            qtyOnStock-=colQty.getCellData(i);
                            break;
                        }
                    }
                }
            }
            isUpdate=false;
            txtQtyOnStock.setText(String.valueOf(qtyOnStock));
            txtUnitPrice.setText(String.valueOf(fabric.getUnitPrice()));
            txtCost.requestFocus();
        }
    }

    @FXML
    void measurementKeyTyped(KeyEvent event) {
        if (!event.getCharacter().matches("[\\d\\.]")) {
            event.consume();
        }
        for (TextField textField : Arrays.asList(
                txtLength,
                txtNeck,
                txtBust,
                txtDownAShoulder,
                txtUnderBust,
                txtSleeve,
                txtWest,
                txtChest,

                txtShirtLength,
                txtChestBoy,
                txtDownAShoulderBoy,
                txtNeckBoy,
                txtSleeveBoy,

                txtCrotch,
                txtKnee,
                txtCalf,
                txtHipAndWest,
                txtInseam,
                txtThigh,
                txtHip,

                txtWestFrock,
                txtHipFrock,
                txtSkirtLength,
                txtFrockLength,
                txtSleeveFrock
        )) {
            textField.setStyle("-fx-border-color: black");
        }

    }

    @FXML
    void tblClickOnAction(MouseEvent event) {
        if (tblOrder.getItems().isEmpty() || tblOrder.getSelectionModel().isEmpty())
            return;
        isUpdate=true;
        btnUpdate.setVisible(true);
        btnAdd.setVisible(false);
        GetOrderTM tm=tblOrder.getSelectionModel().getSelectedItem();
        String measId=tm.getMeasurementId();
        lblMeasurementId.setText(measId);
        dpReceiveDate.setValue(LocalDate.parse(tm.getReceiveDate()));
        dpReturnDate.setValue(LocalDate.parse(tm.getReturnDate()));
        cmbFabricId.setValue(null);
        cmbFabricId.setValue(tm.getFabricId());
        txtCost.setText(String.valueOf(tm.getCost()));
        txtQty.setText(String.valueOf(tm.getQty()));
        int index=searchIndex(measId);
        if (index != -1){
            MeasurementDTO meas=measurements.get(index);
            cmbDressType.setValue(meas.getDetails());
            txtLength.setText(String.valueOf(meas.getLength()));
            txtNeck.setText(String.valueOf(meas.getNeck()));
            txtBust.setText(String.valueOf(meas.getBust()));
            txtDownAShoulder.setText(String.valueOf(meas.getDownAShoulder()));
            txtUnderBust.setText(String.valueOf(meas.getUnderBust()));
            txtSleeve.setText(String.valueOf(meas.getSleeve()));
            txtWest.setText(String.valueOf(meas.getWest()));
            txtChest.setText(String.valueOf(meas.getChest()));
            txtShirtLength.setText(String.valueOf(meas.getLength()));
            txtChestBoy.setText(String.valueOf(meas.getChest()));
            txtDownAShoulderBoy.setText(String.valueOf(meas.getDownAShoulder()));
            txtNeckBoy.setText(String.valueOf(meas.getNeck()));
            txtSleeveBoy.setText(String.valueOf(meas.getSleeve()));
            txtCrotch.setText(String.valueOf(meas.getCrotch()));
            txtKnee.setText(String.valueOf(meas.getKnee()));
            txtCalf.setText(String.valueOf(meas.getCalf()));
            txtHipAndWest.setText(String.valueOf(meas.getHipAndWestDistance()));
            txtInseam.setText(String.valueOf(meas.getInseam()));
            txtThigh.setText(String.valueOf(meas.getThigh()));
            txtHip.setText(String.valueOf(meas.getHip()));
            txtWestFrock.setText(String.valueOf(meas.getWest()));
            txtHipFrock.setText(String.valueOf(meas.getHip()));
            txtSkirtLength.setText(String.valueOf(meas.getSkirtLength()));
            txtFrockLength.setText(String.valueOf(meas.getFrockLength()));
            txtSleeveFrock.setText(String.valueOf(meas.getSleeve()));
        }
    }

    private int searchIndex(String measId) {
        for (int i=0;i<measurements.size();i++){
            if (measurements.get(i).getMeasurementId().equals(measId)){
                return i;
            }
        }
        return -1;
    }


    @FXML
    void txtAdvanceKeyTypedOnAction(KeyEvent event) {
        txtAdvance.setStyle("-fx-border-color: black");
        if (!event.getCharacter().matches("[\\d\\.]")) {
            event.consume();
        }
    }

    @FXML
    void txtAdvanceOnAction(ActionEvent event) {
        txtTotalDescription.requestFocus();
    }

    @FXML
    void txtBustOnAction(ActionEvent event) {
        txtDownAShoulder.requestFocus();
    }

    @FXML
    void txtCalfOnAction(ActionEvent event) {
        txtHipAndWest.requestFocus();
    }

    @FXML
    void txtChestBoyOnAction(ActionEvent event) {
        txtDownAShoulderBoy.requestFocus();
    }

    @FXML
    void txtChestOnAction(ActionEvent event) {
        cmbFabricId.requestFocus();
    }

    @FXML
    void txtCostKeyTypedOnAction(KeyEvent event) {
        txtCost.setStyle("-fx-border-color: black");
        if (!event.getCharacter().matches("[\\d\\.]")) {
            event.consume();
        }
    }

    @FXML
    void txtCostOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }

    @FXML
    void txtCrotchOnAction(ActionEvent event) {
        txtKnee.requestFocus();
    }

    @FXML
    void txtDownAShoulderBoyOnAction(ActionEvent event) {
        txtNeckBoy.requestFocus();
    }

    @FXML
    void txtDownAShoulderOnAction(ActionEvent event) {
        txtUnderBust.requestFocus();
    }

    @FXML
    void txtFrockLengthOnAction(ActionEvent event) {
        txtSleeveFrock.requestFocus();
    }

    @FXML
    void txtHipAndWestOnAction(ActionEvent event) {
        txtInseam.requestFocus();
    }

    @FXML
    void txtHipFrockOnAction(ActionEvent event) {
        txtSkirtLength.requestFocus();
    }

    @FXML
    void txtHipOnAction(ActionEvent event) {
        cmbFabricId.requestFocus();
    }

    @FXML
    void txtInseamOnAction(ActionEvent event) {
        txtThigh.requestFocus();
    }

    @FXML
    void txtKneeOnAction(ActionEvent event) {
        txtCalf.requestFocus();
    }

    @FXML
    void txtLengthOnAction(ActionEvent event) {
        txtNeck.requestFocus();
    }

    @FXML
    void txtNeckBoyOnAction(ActionEvent event) {
        txtSleeveBoy.requestFocus();
    }

    @FXML
    void txtNeckOnAction(ActionEvent event) {
        txtBust.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    void txtQtyTypedOnAction(KeyEvent event) {
        txtQty.setStyle("-fx-border-color: black");
        if (!event.getCharacter().matches("[\\d]")) {
            event.consume();
        }
    }

    @FXML
    void txtShirtLengthOnAction(ActionEvent event) {
        txtChestBoy.requestFocus();
    }

    @FXML
    void txtSkirtLengthOnAction(ActionEvent event) {
        txtFrockLength.requestFocus();
    }

    @FXML
    void txtSleeveBoyOnAction(ActionEvent event) {
        cmbFabricId.requestFocus();
    }

    @FXML
    void txtSleeveFrockOnAction(ActionEvent event) {
        cmbFabricId.requestFocus();
    }

    @FXML
    void txtSleeveOnAction(ActionEvent event) {
        txtWest.requestFocus();
    }

    @FXML
    void txtThighOnAction(ActionEvent event) {
        txtHip.requestFocus();
    }

    @FXML
    void txtTotalDescriptionOnAction(ActionEvent event) {
        btnConfirmOrderOnAction(event);
    }

    @FXML
    void txtUnderBustOnAction(ActionEvent event) {
        txtSleeve.requestFocus();
    }

    @FXML
    void txtWestFrockOnAction(ActionEvent event) {
        txtHipFrock.requestFocus();
    }

    @FXML
    void txtWestOnAction(ActionEvent event) {
        txtChest.requestFocus();
    }

    @FXML
    public void dpReceiveDateOnAction(ActionEvent actionEvent) {
        dpReceiveDate.setStyle("-fx-border-color: black");
        dpReturnDate.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (dpReceiveDate.getValue()!=null) {
                    setDisable(item.isBefore(dpReceiveDate.getValue()));
                }
            }
        });
    }
    @FXML
    public void dpReturnDateOnAction(ActionEvent actionEvent) {
        dpReturnDate.setStyle("-fx-border-color: black");
        dpReceiveDate.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (dpReturnDate.getValue()!=null) {
                    setDisable(item.isAfter(dpReturnDate.getValue()));
                }
            }
        });
    }
}
