package lk.ijse.tailorshop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SuppliesFormController {

    public Button btnRemove;
    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnAdmin1;

    @FXML
    private Button btnAdmin2;

    @FXML
    private Button btnAdmin3;

    @FXML
    private Button btnAdmin4;

    @FXML
    private Label lblUserName;

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void txtDescriptionOnAction(ActionEvent actionEvent) {
    }

    public void txtDateOnAction(ActionEvent actionEvent) {
    }

    public void btnClickOnAction(MouseEvent mouseEvent) {
    }

    public void btnSuppliesConfirmOnAction(ActionEvent actionEvent) {
    }

    public void cmbFabricIdOnAction(ActionEvent actionEvent) {
    }

    public void txtQtyOnStockOnAction(ActionEvent actionEvent) {
    }

    public void txtUnitPriceOnAction(ActionEvent actionEvent) {
    }

    public void txtAddingQtyOnAction(ActionEvent actionEvent) {
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }
}
