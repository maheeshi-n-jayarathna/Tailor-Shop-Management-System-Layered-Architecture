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
import lk.ijse.tailorshop.dto.FabricDTO;
import lk.ijse.tailorshop.entity.Fabric;
import lk.ijse.tailorshop.service.Custom.FabricService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;
import lk.ijse.tailorshop.service.exception.DuplicateException;
import lk.ijse.tailorshop.service.exception.InUseException;
import lk.ijse.tailorshop.view.tm.FabricTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class FabricFormController {
    public Button btnReset;
    public Button btnUpdate;
    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<FabricTM, String> colFabricId;

    @FXML
    private TableColumn<FabricTM, String> colDescription;

    @FXML
    private TableColumn<FabricTM, Integer> colQtyOnStock;

    @FXML
    private TableColumn<FabricTM, Double> colUnitPrice;

    @FXML
    private TableView<FabricTM> tblFabric;

    @FXML
    private TextField txtFabricId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnStock;

    @FXML
    private TextField txtUnitPrice;

    private final ObservableList<FabricTM> observableArray = FXCollections.observableArrayList();
    private final FabricService fabricService = ServiceFactory.getInstance().getService(ServiceType.FABRIC);
    private Pattern descriptionPattern;
    private Pattern qtyOnStockPattern;
    private Pattern unitPricePattern;

    public void initialize() {

        descriptionPattern = Pattern.compile("^[a-zA-Z0-9]{2,}$");
        qtyOnStockPattern = Pattern.compile("^[0-9]{1,}$");
        unitPricePattern = Pattern.compile("^(\\d+)||((\\d+\\.)(\\d){2})$");
        setTableCellFactory();
        clearAll();
    }

    private void setTableCellFactory() {
        refreshTableData();
        colFabricId.setCellValueFactory(new PropertyValueFactory<>("fabricId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnStock.setCellValueFactory(new PropertyValueFactory<>("qtyOnStock"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
    }

    private void refreshTableData() {
        observableArray.clear();
        for (FabricDTO fabricDTO : fabricService.getAllFabric()){
            observableArray.add(new FabricTM(
                    fabricDTO.getFabricId(),
                    fabricDTO.getDescription(),
                    fabricDTO.getQtyOnStock(),
                    fabricDTO.getUnitPrice()
            ));
        }
        tblFabric.setItems(observableArray);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        boolean isDescriptionMatched = descriptionPattern.matcher(txtDescription.getText()).matches();
        boolean isQtyOnStockMatched = qtyOnStockPattern.matcher(txtQtyOnStock.getText()).matches();
        boolean isUnitPriceMatched = unitPricePattern.matcher(txtUnitPrice.getText()).matches();
        if (!isDescriptionMatched) {
            txtDescription.setStyle("-fx-border-color: red;");
            txtDescription.requestFocus();
        }
        if (!isQtyOnStockMatched) {
            txtQtyOnStock.setStyle("-fx-border-color: red;");
            txtQtyOnStock.requestFocus();
        }
        if (!isUnitPriceMatched) {
            txtUnitPrice.setStyle("-fx-border-color: red;");
            txtUnitPrice.requestFocus();
        }
        if(isDescriptionMatched && isQtyOnStockMatched && isUnitPriceMatched ) {

            String fabricId = txtFabricId.getText();
            String description = txtDescription.getText();
            int qtyOnStock = Integer.parseInt(txtQtyOnStock.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            FabricDTO fabric = new FabricDTO(fabricId, description, qtyOnStock, unitPrice);


            try {
                if (fabricService.saveFabric(fabric)) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Fabric saved !").show();
                    clearAll();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fabric saved fail !");
                }
            } catch (DuplicateException e) {
                txtDescription.setStyle("-fx-border-color: red;");
                txtDescription.selectAll();
                txtDescription.requestFocus();
                new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            }

        }
    }

    private void clearAll() {
        btnAdd.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        txtFabricId.setText(fabricService.getNextFabricId());
        txtDescription.setText("");
        txtQtyOnStock.setText("");
        txtUnitPrice.setText("");
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure ?");
        alert.setTitle("Fabric delete");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            try {
                if (fabricService.deleteFabric(txtFabricId.getText())) {
                    refreshTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Deleted !");
                    clearAll();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Deleting Fail !");
                }
            } catch (InUseException e) {
                new Alert(Alert.AlertType.WARNING, e.getMessage());
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Fabric data updating");
        alert.setTitle("are your sure ?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            boolean isDescriptionMatched = descriptionPattern.matcher(txtDescription.getText()).matches();
            boolean isQtyOnStockMatched = qtyOnStockPattern.matcher(txtQtyOnStock.getText()).matches();
            boolean isUnitPriceMatched = unitPricePattern.matcher(txtUnitPrice.getText()).matches();
            if (!isDescriptionMatched) {
                txtDescription.setStyle("-fx-border-color: red;");
                txtDescription.requestFocus();
            }
            if (!isQtyOnStockMatched) {
                txtQtyOnStock.setStyle("-fx-border-color: red;");
                txtQtyOnStock.requestFocus();
            }
            if (!isUnitPriceMatched) {
                txtUnitPrice.setStyle("-fx-border-color: red;");
                txtUnitPrice.requestFocus();
            }
            if(isDescriptionMatched && isQtyOnStockMatched && isUnitPriceMatched ) {

                String fabricId = txtFabricId.getText();
                String description = txtDescription.getText();
                int qtyOnStock = Integer.parseInt(txtQtyOnStock.getText());
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());

                FabricDTO fabric = new FabricDTO(fabricId, description, qtyOnStock, unitPrice);

                try {
                    if (fabricService.updateFabric(fabric)) {
                        refreshTableData();
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                        clearAll();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Update fail").show();
                    }
                } catch (DuplicateException e) {
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
        FabricTM fabric = tblFabric.getSelectionModel().getSelectedItem();
        txtFabricId.setText(fabric.getFabricId());
        txtDescription.setText(fabric.getDescription());
        txtQtyOnStock.setText(String.valueOf(fabric.getQtyOnStock()));
        txtUnitPrice.setText(String.valueOf(fabric.getUnitPrice()));
    }

    public void txtDescriptionOnAction(ActionEvent actionEvent) {
        txtQtyOnStock.requestFocus();
    }

    @FXML
    void txtQtyOnStockOnAction(ActionEvent event) {
        txtUnitPrice.requestFocus();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

}











