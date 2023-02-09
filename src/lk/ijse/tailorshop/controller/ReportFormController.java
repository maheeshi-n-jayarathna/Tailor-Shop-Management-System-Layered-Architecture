package lk.ijse.tailorshop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tailorshop.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class ReportFormController {

    public Button btnClose;
    @FXML
    private AnchorPane anc;


    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void getIncomeReport(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("../report/income.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {

        }
    }

    public void customerReport(ActionEvent actionEvent) {
        InputStream inputStream = this.getClass().getResourceAsStream("../report/CustomerReport.jrxml");
        try {
            JasperReport compileReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport,null, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {

        }
    }
}
