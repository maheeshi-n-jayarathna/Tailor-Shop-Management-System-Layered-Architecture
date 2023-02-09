package lk.ijse.tailorshop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class DashBoardFormController {
    @FXML
    private AnchorPane anc;

    @FXML
    private BarChart barChart;

    @FXML
    private Button btnClose;

    @FXML
    private Label lblMonthlyIncome;

    @FXML
    private Label lblSoldItem;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblTotalOrders;

    @FXML
    private Label lblTotalSales;

    public DashBoardFormController() {
    }

    /*public void initialize() {
        this.loadLabel();
        this.loadBarChart();
        //this.loadScrollPane();
    }
*/
    public void btnCloseOnAction(ActionEvent actionEvent) {
            Platform.exit();
    }

    /*private void loadBarChart() {
        try {
            XYChart.Series<String, Integer> series = new XYChart.Series();

            for(int i = 1; i < 16; ++i) {
                LocalDate date = Project.sub(-(15 - i));
                int count = TransactionModel.searchDateTransactionCount(date);
                series.getData().add(new XYChart.Data(date.toString(), count));
            }

            this.barChart.getData().addAll(new Object[]{series});
        } catch (ClassNotFoundException | SQLException var5) {
            ButtonType result = Project.showError(Alert.AlertType.CONFIRMATION, "Data Not Loading", var5 + ",Data Not Loading !\nYou Want Exit ? ");
            if (result == ButtonType.OK) {
                Platform.exit();
            }
        }

    }

    /*private void loadLabel() {
        try {
            this.lblTotalCustomers.setText(String.valueOf(CustomerModel.getCustomerCount()));
            this.lblTotalOrders.setText(String.valueOf(OrderModel.getOrderCount()));
            this.lblTodayIncome.setText(NumberFormat.getCurrencyInstance(new Locale("en", "in")).format(OrderModel.getTodayAllIncome()));
            this.lblTotalIncomeToDay.setText
            this.lblTotalTransactionToDay.setText(String.valueOf(TransactionModel.searchDateTransactionCount(LocalDate.now())));
        } catch (ClassNotFoundException | SQLException var3) {
            ButtonType result = Project.showError(Alert.AlertType.CONFIRMATION, "Data Not Loading", var3 + ",Data Not Loading !\nYou Want Exit ? ");
            if (result == ButtonType.OK) {
                Platform.exit();
            }
        }

    }*/
}
