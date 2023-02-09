package lk.ijse.tailorshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class SideBarFormController {

    @FXML
    private Pane Slider;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane bpAnc;

    @FXML
    private Button btnAdmin;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblName;

    @FXML
    private Label lblRank;

    @FXML
    private VBox vBoxAdmin;

    @FXML
    private VBox vBoxUser;
    private static String name;
    private static boolean isAdmin;
    public static void setUSer(String name, boolean isAdmin) {
        SideBarFormController.name = name;
        SideBarFormController.isAdmin = isAdmin;
    }

    public void initialize() throws IOException {
        loadDate();
        loadProfile();
        if (isAdmin){
            vBoxAdmin.setVisible(true);
            vBoxUser.setVisible(false);
            lblRank.setText("Admin");
            btnAdmin.setVisible(true);
            goAdminOnAction();
        }else {
            vBoxUser.setVisible(true);
            vBoxAdmin.setVisible(false);
            lblRank.setText("Supervisor");
            btnAdmin.setVisible(false);
            goDashboardOnAction(new ActionEvent());
        }
    }

    private void loadProfile() {
        lblName.setText(name);
    }

    private void loadDate() {
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void initUI(String location) {
        try {
            bpAnc.getChildren().clear();
            bpAnc.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + location)));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Forms error ...!");
        }
    }

    @FXML
    void goAdminOnAction() throws IOException {
        vBoxAdmin.setVisible(true);
        vBoxUser.setVisible(false);
        goSummaryOnAction();
    }

    @FXML
    void goCustomerOnAction(ActionEvent event) throws IOException {
        initUI("CustomerForm.fxml");
    }

    @FXML
    void goDashboardOnAction(ActionEvent event) throws IOException {
        vBoxUser.setVisible(true);
        vBoxAdmin.setVisible(false);
        initUI("DashBoardForm.fxml");
    }

    @FXML
    void goEmployeesOnAction(ActionEvent event) throws IOException {
        initUI("EmployeeForm.fxml");
    }

    @FXML
    void goFabricOnAction(ActionEvent event) throws IOException {
        initUI("FabricForm.fxml");
    }

    @FXML
    void goGetOrderOnAction(ActionEvent event) throws IOException {
        initUI("GetOrderForm.fxml");
    }

    @FXML
    void goLogOutOnAction(ActionEvent event) throws IOException {
        try {
            bp.getChildren().clear();
            bp.getChildren().add(FXMLLoader.load(getClass().getResource("../view/loginForm.fxml")));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Forms error ...!");
        }
    }

    @FXML
    void goPurchaseOnAction(ActionEvent event) throws IOException {
        initUI("PurchaseOrderForm.fxml");
    }

    @FXML
    void goReportOnAction(ActionEvent event) throws IOException {
        initUI("ReportsForm.fxml");
    }

    @FXML
    void goSummaryOnAction() {
        initUI("DashBoardForm.fxml");
    }

    @FXML
    void goSuppliersOnAction(ActionEvent event) throws IOException {
        initUI("SupplierForm.fxml");
    }

    @FXML
    void goSuppliesOnAction(ActionEvent event) throws IOException {
        initUI("SuppliesForm.fxml");
    }

    @FXML
    void goUserOnAction(ActionEvent event) throws IOException {
        initUI("UserForm.fxml");
    }

    @FXML
    void goViewOrderOnAction(ActionEvent event) throws IOException {
        initUI("ViewOrderForm.fxml");
    }

}
