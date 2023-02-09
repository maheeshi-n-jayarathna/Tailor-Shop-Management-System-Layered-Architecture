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
import lk.ijse.tailorshop.dao.custom.EmployeeDAO;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.service.Custom.EmployeeService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.view.tm.EmployeeTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeFormController {
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
    private TableColumn<EmployeeTM, String> colAddress;

    @FXML
    private TableColumn<EmployeeTM, String> colEmail;

    @FXML
    private TableColumn<EmployeeTM, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeTM, String> colName;

    @FXML
    private TableColumn<EmployeeTM, String> colNic;

    @FXML
    private TableColumn<EmployeeTM, String> colPhoneNumber;

    @FXML
    private TableColumn<EmployeeTM, String> colRank;

    @FXML
    private Label lblUserName;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtRank;

    private final ObservableList<EmployeeTM> observableArray = FXCollections.observableArrayList();
    private final EmployeeService employeeService = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
    private Pattern namePattern;
    private Pattern rankPattern;
    private Pattern nicPattern;
    private Pattern telNumberPattern;
    private Pattern emailPattern;

    public void initialize() {

        namePattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        rankPattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        nicPattern = Pattern.compile("^[0-9]{9}[vV]||[0-9]{12}$");
        telNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        setTableCellFactory();
        clearAll();
    }

    private void setTableCellFactory() {
        refreshTableData();
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void refreshTableData() {
        observableArray.clear();
        for (EmployeeDTO employeeDTO : employeeService.getAllEmployee()) {
            observableArray.add(new EmployeeTM(employeeDTO.getEmployeeId(), employeeDTO.getName(), employeeDTO.getRank(), employeeDTO.getNic(), employeeDTO.getTelNumber(), employeeDTO.getEmail(), employeeDTO.getAddress()));
        }
        tblEmployee.setItems(observableArray);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
        boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
        boolean isRankMatched = rankPattern.matcher(txtRank.getText()).matches();
        boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
        boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
        boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
        if (!isNameMatched) {
            txtName.setStyle("-fx-border-color: red;");
            txtName.requestFocus();
        }
        if (!isRankMatched) {
            txtRank.setStyle("-fx-border-color: red;");
            txtRank.requestFocus();
        }
        if (!isAddressMatcher) {
            txtAddress.setStyle("-fx-border-color: red;");
            txtAddress.requestFocus();
        }
        if (!isTelNumberMatched) {
            txtPhoneNumber.setStyle("-fx-border-color: red;");
            txtPhoneNumber.requestFocus();
        }
        if (!isNicMatched) {
            txtNic.setStyle("-fx-border-color: red;");
            txtNic.requestFocus();
        }
        if (!isEmailMatched) {
            txtEmail.setStyle("-fx-border-color: red;");
            txtEmail.requestFocus();
        }
        if(isNameMatched && isRankMatched && isNicMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

            String employeeId = txtEmployeeId.getText();
            String name = txtName.getText();
            String nic = txtNic.getText();
            String rank = txtRank.getText();
            String telNumber = txtPhoneNumber.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();

            EmployeeDTO employee = new EmployeeDTO(employeeId, name, nic, rank, telNumber, email, address);

            try {
                if (employeeService.saveEmployee(employee)) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Employee saved!").show();
                    clearAll();
                } else {
                    new  Alert(Alert.AlertType.WARNING, "Fail to save the employee").show();
                }
            } catch (DuplicateException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    private void clearAll() {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtEmployeeId.setText(employeeService.getNextEmployeeId());
        txtName.setText("");
        txtNic.setText("");
        txtRank.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?");
        alert.setTitle("Employee delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            if (employeeService.deleteEmployee(txtEmployeeId.getText())) {
                refreshTableData();
                new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delete Fail!");
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Employee data updating");
        alert.setTitle("are your sure ?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            boolean isNameMatched = namePattern.matcher(txtName.getText()).matches();
            boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
            boolean isRankMatched = rankPattern.matcher(txtRank.getText()).matches();
            boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
            boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
            boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
            if (!isNameMatched) {
                txtName.setStyle("-fx-border-color: red;");
                txtName.requestFocus();
            }
            if (!isRankMatched) {
                txtRank.setStyle("-fx-border-color: red;");
                txtRank.requestFocus();
            }
            if (!isAddressMatcher) {
                txtAddress.setStyle("-fx-border-color: red;");
                txtAddress.requestFocus();
            }
            if (!isTelNumberMatched) {
                txtPhoneNumber.setStyle("-fx-border-color: red;");
                txtPhoneNumber.requestFocus();
            }
            if (!isNicMatched) {
                txtNic.setStyle("-fx-border-color: red;");
                txtNic.requestFocus();
            }
            if (!isEmailMatched) {
                txtEmail.setStyle("-fx-border-color: red;");
                txtEmail.requestFocus();
            }
            if(isNameMatched && isRankMatched && isNicMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

                String employeeId = txtEmployeeId.getText();
                String name = txtName.getText();
                String nic = txtNic.getText();
                String rank = txtRank.getText();
                String telNumber = txtPhoneNumber.getText();
                String email = txtEmail.getText();
                String address = txtAddress.getText();

                EmployeeDTO employee = new EmployeeDTO(employeeId, name, nic, rank, telNumber, email, address);

                try {
                    if (employeeService.updateEmployee(employee)) {
                        refreshTableData();
                        new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                        clearAll();
                    } else {
                        new  Alert(Alert.AlertType.WARNING, "Fail to Update the employee").show();
                    }
                } catch (DuplicateException e) {
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
        EmployeeTM employee = tblEmployee.getSelectionModel().getSelectedItem();
        txtEmployeeId.setText(employee.getEmployeeId());
        txtName.setText(employee.getName());
        txtNic.setText(employee.getNic());
        txtRank.setText(employee.getRank());
        txtAddress.setText(employee.getAddress());
        txtEmail.setText(employee.getEmail());
        txtPhoneNumber.setText(employee.getTelNumber());
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtRank.requestFocus();
    }

    @FXML
    void txtRankOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }

    public void txtPhoneNumberOnAction(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }

    public void txtNicOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

}











