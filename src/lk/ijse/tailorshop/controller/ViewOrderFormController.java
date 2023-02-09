package lk.ijse.tailorshop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ViewOrderFormController {

    @FXML
    private AnchorPane anc;

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFabricId;

    @FXML
    private TableColumn<?, ?> colMeasurementId;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colReceiveDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableColumn<?, ?> colTotalAmount;

    @FXML
    private Label lblReturnedDate;

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

}
