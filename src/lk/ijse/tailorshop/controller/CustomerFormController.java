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
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.service.Custom.CustomerService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {
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
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colCustomerId;

    @FXML
    private TableColumn<CustomerTM, String> colEmail;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private TableColumn<CustomerTM, String> colNic;

    @FXML
    private TableColumn<CustomerTM, String> colPhoneNumber;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtPhoneNumber;

    private final ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
    private final CustomerService customerService = ServiceFactory.getInstance().getService(ServiceType.CUSTOMER);
    private Pattern namePattern;
    private Pattern nicPattern;
    private Pattern telNumberPattern;
    private Pattern emailPattern;

    public void initialize() {
        namePattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        nicPattern = Pattern.compile("^[0-9]{9}[vV]||[0-9]{12}$");
        telNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        setTableCellFactory();
        clearAll();
    }

    private void setTableCellFactory() {
        refreshTableData();
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void refreshTableData() {
        customerTMS.clear();
        for (CustomerDTO customerDTO : customerService.getAllCustomer()){
            customerTMS.add(new CustomerTM(
               customerDTO.getCustomerId(),
               customerDTO.getName(),
               customerDTO.getNic(),
               customerDTO.getTelNumber(),
               customerDTO.getEmail(),
               customerDTO.getAddress()
            ));
        }
        tblCustomer.setItems(customerTMS);

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
        boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
        boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
        boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
        boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
        if (!isNameMatched){
            txtName.setStyle("-fx-border-color: red;");
            txtName.requestFocus();
        }
        if (!isNicMatched){
            txtNic.setStyle("-fx-border-color: red;");
            txtNic.requestFocus();
        }
        if (!isTelNumberMatched){
            txtPhoneNumber.setStyle("-fx-border-color: red;");
            txtPhoneNumber.requestFocus();
        }
        if (!isEmailMatched) {
            txtEmail.setStyle("-fx-border-color: red;");
            txtEmail.requestFocus();
        }
        if (!isAddressMatcher){
            txtAddress.setStyle("-fx-border-color: red;");
            txtAddress.requestFocus();
        }
        if(isNameMatched && isNicMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

            String customerId = txtCustomerId.getText();
            String name = txtName.getText();
            String nic = txtNic.getText();
            String telNumber = txtPhoneNumber.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();

            try {
                CustomerDTO customerDTO = new CustomerDTO(customerId, name, nic, telNumber, email, address);

                if (customerService.saveCustomer(customerDTO)) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Added!").show();
                    clearAll();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Fail to save customer !").show();
                }
            } catch (DuplicateException e){
                txtNic.selectAll();
                txtNic.setStyle("-fx-border-color: red;");
                txtNic.requestFocus();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearAll() {
        txtCustomerId.setText(customerService.getNextCustomerId());
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtName.setText("");
        txtNic.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?");
        alert.setTitle("Customer delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get()==ButtonType.OK) {
            try {
                if(customerService.deleteCustomer(txtCustomerId.getText())) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted !");
                    clearAll();
                }else {
                    new Alert(Alert.AlertType.WARNING, "Fail to delete the customer !");
                }
            } catch (InUseException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Customer data updating");
        alert.setTitle("are your sure ?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get()==ButtonType.OK) {
            boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
            boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
            boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
            boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
            boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
            if (!isNameMatched){
                txtName.setStyle("-fx-border-color: red;");
                txtName.requestFocus();
            }
            if (!isNicMatched){
                txtNic.setStyle("-fx-border-color: red;");
                txtNic.requestFocus();
            }
            if (!isTelNumberMatched){
                txtPhoneNumber.setStyle("-fx-border-color: red;");
                txtPhoneNumber.requestFocus();
            }
            if (!isEmailMatched){
                txtEmail.setStyle("-fx-border-color: red;");
                txtEmail.requestFocus();
            }if (!isAddressMatcher){
                txtAddress.setStyle("-fx-border-color: red;");
                txtAddress.requestFocus();
            }
            if(isNameMatched && isNicMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

                String customerId = txtCustomerId.getText();
                String name = txtName.getText();
                String nic = txtNic.getText();
                String telNumber = txtPhoneNumber.getText();
                String email = txtEmail.getText();
                String address = txtAddress.getText();

                CustomerDTO customer = new CustomerDTO(customerId, name, nic, telNumber, email, address);

                try {
                    if(customerService.updateCustomer(customer)) {
                        refreshTableData();
                        new Alert(Alert.AlertType.INFORMATION, "Customer updated!").show();
                        clearAll();
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Fail to update the customer !");
                    }
                } catch (DuplicateException e){
                    txtNic.selectAll();
                    txtNic.setStyle("-fx-border-color: red;");
                    txtNic.requestFocus();
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
        CustomerTM customer = tblCustomer.getSelectionModel().getSelectedItem();
        txtCustomerId.setText(customer.getCustomerId());
        txtName.setText(customer.getName());
        txtNic.setText(customer.getNic());
        txtAddress.setText(customer.getAddress());
        txtEmail.setText(customer.getEmail());
        txtPhoneNumber.setText(customer.getPhoneNumber());
    }

    public void txtNameOnActon(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }

    public void txtNixOnAction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }


    public void txtEmailOnAction(ActionEvent actionEvent) {

        txtAddress.requestFocus();
    }

    public void txtPhoneNumberOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}

