package lk.ijse.tailorshop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.dto.SupplierDTO;
import lk.ijse.tailorshop.service.Custom.SupplierService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.view.tm.SupplierTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SupplierFormController {
    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierId;

    @FXML
    private TableColumn<SupplierTM, String> colName;

    @FXML
    private TableColumn<SupplierTM, String> colDescription;

    @FXML
    private TableColumn<SupplierTM, String> colPhoneNumber;

    @FXML
    private TableColumn<SupplierTM, String> colAddress;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    private final ObservableList<SupplierTM> observableArray = FXCollections.observableArrayList();
    private final SupplierService supplierService = ServiceFactory.getInstance().getService(ServiceType.SUPPLIER);
    private Pattern namePattern;
    private Pattern descriptionPattern;
    private Pattern telNumberPattern;
    public void initialize() {

        namePattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        descriptionPattern = Pattern.compile("^[a-zA-Z0-9]{2,}$");
        telNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        setTableCellFactory();
        clearAll();
    }

    private void setTableCellFactory() {
        refreshTableData();
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void refreshTableData() {
        observableArray.clear();
        observableArray.addAll(supplierService.getAllSupplier().stream().map(s -> new SupplierTM(s.getSupplierId(),s.getName(),s.getDescription(),s.getTelNumber(),s.getAddress())).collect(Collectors.toList()));
        tblSupplier.setItems(observableArray);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
        boolean isDescriptionMatched = descriptionPattern.matcher(txtDescription.getText()).matches();
        boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
        if (!isNameMatched) {
            txtName.setStyle("-fx-border-color: red;");
            txtName.requestFocus();
        } else if (!isDescriptionMatched) {
            txtDescription.setStyle("-fx-border-color: red;");
            txtDescription.requestFocus();
        } else if (!isTelNumberMatched) {
            txtPhoneNumber.setStyle("-fx-border-color: red;");
            txtPhoneNumber.requestFocus();
        } else {

            String supplierId = txtSupplierId.getText();
            String name = txtName.getText();
            String description = txtDescription.getText();
            String telNumber = txtPhoneNumber.getText();
            String address = txtAddress.getText();

            SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, description, telNumber, address);

            if (supplierService.saveSupplier(supplierDTO)) {
                refreshTableData();
                new Alert(Alert.AlertType.INFORMATION, "Supplier Added !").show();
                clearAll();
            } else {
               new Alert(Alert.AlertType.ERROR, "supplier data adding fail !").show();
            }
        }
    }

    private void clearAll() {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtSupplierId.setText(supplierService.getNextSupplierId());
        txtName.setText("");
        txtDescription.setText("");;
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?");
        alert.setTitle("Supplier delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            if (supplierService.deleteSupplier(txtSupplierId.getText())) {
                refreshTableData();
                new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delete Fail!");
            }

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supplier data updating");
        alert.setTitle("are your sure ?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
            boolean isDescriptionMatched = descriptionPattern.matcher(txtDescription.getText()).matches();
            boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
            if (!isNameMatched) {
                txtName.setStyle("-fx-border-color: red;");
                txtName.requestFocus();
            } else if (!isDescriptionMatched) {
                txtDescription.setStyle("-fx-border-color: red;");
                txtDescription.requestFocus();
            } else if (!isTelNumberMatched) {
                txtPhoneNumber.setStyle("-fx-border-color: red;");
                txtPhoneNumber.requestFocus();
            } else if (txtAddress.getText().isEmpty()) {
                txtAddress.setStyle("-fx-border-color: red;");
                txtAddress.requestFocus();
            } else {
                String supplierId = txtSupplierId.getText();
                String name = txtName.getText();
                String description = txtDescription.getText();
                String telNumber = txtPhoneNumber.getText();
                String address = txtAddress.getText();

                SupplierDTO supplierDTO = new SupplierDTO(supplierId, name, description, telNumber, address);
                if (supplierService.updateSupplier(supplierDTO)) {
                    refreshTableData();
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                    clearAll();
                } else {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update fail").show();
                }
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        clearAll();
    }


    public void tblClickOnAction(MouseEvent mouseEvent) {
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
        SupplierTM supplier = tblSupplier.getSelectionModel().getSelectedItem();
        txtSupplierId.setText(supplier.getSupplierId());
        txtName.setText(supplier.getName());
        txtDescription.setText(supplier.getDescription());
        txtAddress.setText(supplier.getAddress());
        txtPhoneNumber.setText(supplier.getTelNumber());
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtDescription.requestFocus();
    }

    @FXML
    void txtDescriptionOnAction(ActionEvent event) {
        txtPhoneNumber.requestFocus();
    }
    public void txtPhoneNumberOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }
    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }


}











