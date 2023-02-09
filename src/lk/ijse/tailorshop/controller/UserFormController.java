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
import lk.ijse.tailorshop.dto.UserDTO;
import lk.ijse.tailorshop.service.Custom.UserService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.view.tm.UserTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserFormController {
    public TableColumn colUserId;
    public Button btnReset;
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
    private TableColumn<UserTM, String> colUserID;

    @FXML
    private TableColumn<UserTM, String> colUserRank;

    @FXML
    private TableColumn<UserTM, String> colUserName;

    @FXML
    private TableColumn<UserTM, String> colPassword;

    @FXML
    private TableColumn<UserTM, String> colNic;

    @FXML
    private TableColumn<UserTM, String> colPhoneNumber;

    @FXML
    private TableColumn<UserTM, String> colEmail;

    @FXML
    private TableColumn<UserTM, String> colAddress;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserRank;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    private final ObservableList<UserTM> observableArray = FXCollections.observableArrayList();
    private final UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);
    private Pattern userNamePattern;
    private Pattern userRankPattern;
    private Pattern passwordPattern;

    private Pattern nicPattern;
    private Pattern telNumberPattern;
    private Pattern emailPattern;

    public void initialize() {

        userRankPattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        userNamePattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        passwordPattern = Pattern.compile("^[a-zA-Z]{5,100}$");
        nicPattern = Pattern.compile("^[0-9]{9}[vV]||[0-9]{12}$");
        telNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        setTableCellFactory();
        clearAll();
    }

    private void setTableCellFactory() {
        refreshTableData();
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserRank.setCellValueFactory(new PropertyValueFactory<>("userRank"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void refreshTableData() {
        observableArray.clear();
        observableArray.addAll(userService.getAllUser().stream().map(userDTO -> new UserTM(userDTO.getUserId(), userDTO.getUserRank(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getNic(), userDTO.getTelNumber(), userDTO.getEmail(), userDTO.getAddress())).collect(Collectors.toList()));
        tblUser.setItems(observableArray);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        boolean isUserRankMatched = userRankPattern.matcher(txtUserRank.getText()).matches();
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPasswordMatched = passwordPattern.matcher(txtPassword.getText()).matches();
        boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
        boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
        boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
        boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
        if (!isUserRankMatched) {
            txtUserRank.setStyle("-fx-border-color: red;");
            txtUserRank.requestFocus();
        }
        if (!isUserNameMatched) {
            txtUserName.setStyle("-fx-border-color: red;");
            txtUserName.requestFocus();
        }
        if (!isPasswordMatched) {
            txtPassword.setStyle("-fx-border-color: red;");
            txtPassword.requestFocus();
        }
        if (!isNicMatched) {
            txtNic.setStyle("-fx-border-color: red;");
            txtNic.requestFocus();
        }
        if (!isTelNumberMatched) {
            txtPhoneNumber.setStyle("-fx-border-color: red;");
            txtPhoneNumber.requestFocus();
        }
        if (!isEmailMatched) {
            txtEmail.setStyle("-fx-border-color: red;");
            txtEmail.requestFocus();
        }
        if (!isAddressMatcher) {
            txtAddress.setStyle("-fx-border-color: red;");
            txtAddress.requestFocus();
        }if(isUserRankMatched &&  isUserNameMatched && isPasswordMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

            String userId = txtUserId.getText();
            String userRank = txtUserRank.getText();
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            String nic = txtNic.getText();
            String telNumber = txtPhoneNumber.getText();
            String email = txtEmail.getText();
            String address = txtAddress.getText();

            UserDTO userDTO = new UserDTO(userId, userRank, userName, password, nic, telNumber, email, address);
            try {
                if (userService.saveUser(userDTO)) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "User Added !").show();
                    clearAll();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Fail to save user !");
                }
            } catch (DuplicateException e) {
                txtUserName.setStyle("-fx-border-color: red;");
                txtUserName.selectAll();
                txtUserName.requestFocus();
                new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            }

        }
    }

    private void clearAll() {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtUserId.setText(userService.getNextUserId());
        txtUserRank.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtNic.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?");
        alert.setTitle("User delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            if (userService.deleteUser(txtUserId.getText())) {
                refreshTableData();
                new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delete Fail!");
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "User data updating");
        alert.setTitle("are your sure ?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            boolean isUserRankMatched = userRankPattern.matcher(txtUserRank.getText()).matches();
            boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
            boolean isPasswordMatched = passwordPattern.matcher(txtPassword.getText()).matches();
            boolean isNicMatched = nicPattern.matcher(txtNic.getText()).matches();
            boolean isTelNumberMatched = telNumberPattern.matcher(txtPhoneNumber.getText()).matches();
            boolean isEmailMatched = emailPattern.matcher(txtEmail.getText()).matches();
            boolean isAddressMatcher=!(txtAddress.getText().isEmpty());
            if (!isUserRankMatched) {
                txtUserRank.setStyle("-fx-border-color: red;");
                txtUserRank.requestFocus();
            }
            if (!isUserNameMatched) {
                txtUserName.setStyle("-fx-border-color: red;");
                txtUserName.requestFocus();
            }
            if (!isPasswordMatched) {
                txtPassword.setStyle("-fx-border-color: red;");
                txtPassword.requestFocus();
            }
            if (!isNicMatched) {
                txtNic.setStyle("-fx-border-color: red;");
                txtNic.requestFocus();
            }
            if (!isTelNumberMatched) {
                txtPhoneNumber.setStyle("-fx-border-color: red;");
                txtPhoneNumber.requestFocus();
            }
            if (!isEmailMatched) {
                txtEmail.setStyle("-fx-border-color: red;");
                txtEmail.requestFocus();
            }
            if (!isAddressMatcher) {
                txtAddress.setStyle("-fx-border-color: red;");
                txtAddress.requestFocus();
            }if(isUserRankMatched &&  isUserNameMatched && isPasswordMatched && isTelNumberMatched && isEmailMatched && isAddressMatcher) {

                String userId = txtUserId.getText();
                String userRank = txtUserRank.getText();
                String userName = txtUserName.getText();
                String password = txtPassword.getText();
                String nic = txtNic.getText();
                String telNumber = txtPhoneNumber.getText();
                String email = txtEmail.getText();
                String address = txtAddress.getText();

                UserDTO userDTO = new UserDTO(userId, userRank, userName, password, nic, telNumber, email, address);

                try {
                    if (userService.updateUser(userDTO)) {
                        refreshTableData();
                        new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
                        clearAll();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Update fail").show();
                    }
                } catch (DuplicateException e) {
                    txtUserName.setStyle("-fx-border-color: red;");
                    txtUserName.selectAll();
                    txtUserName.requestFocus();
                    new Alert(Alert.AlertType.WARNING, e.getMessage());
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
        UserTM user = tblUser.getSelectionModel().getSelectedItem();
        txtUserId.setText(user.getUserId());
        txtUserRank.setText(user.getUserRank());
        txtUserName.setText(user.getUserName());
        txtPassword.setText(user.getPassword());
        txtNic.setText(user.getNic());
        txtAddress.setText(user.getAddress());
        txtEmail.setText(user.getEmail());
        txtPhoneNumber.setText(user.getTelNumber());
    }

    public void txtUserRankOnAction(ActionEvent actionEvent) {
        txtUserName.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }
    public void txtNicOnAction(ActionEvent actionEvent) {
        txtPhoneNumber.requestFocus();
    }
    public void txtPhoneNumberOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }
    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {

        Platform.exit();
    }

}











