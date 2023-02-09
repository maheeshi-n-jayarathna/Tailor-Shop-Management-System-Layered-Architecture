package lk.ijse.tailorshop.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.tailorshop.dto.UserDTO;
import lk.ijse.tailorshop.service.Custom.UserService;
import lk.ijse.tailorshop.service.ServiceFactory;
import lk.ijse.tailorshop.service.ServiceType;

import java.io.IOException;

public class LoginFormController {
    @FXML
    public Text lblLogin;
    @FXML
    public Label lblChange;
    public AnchorPane ancPane;
    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    boolean isAdmin=true;
    @FXML
    private Label lblError;

    private final UserService userService = ServiceFactory.getInstance().getService(ServiceType.USER);

    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        String name=txtUserName.getText();
        String password=txtPassword.getText();
        String rank=null;
        String error = "";
        if (isAdmin)
            rank="Admin";
        else
            rank="Supervisor";
        if (name.isEmpty()) {
            error = "Please Enter User Name";
            txtUserName.setStyle("-fx-background-color: #b33939");
            txtUserName.requestFocus();
        }else if (password.isEmpty()){
            error = "Please Enter Password";
            txtUserName.setStyle("-fx-background-color: #b33939");
            txtPassword.requestFocus();
        }else {
            UserDTO userDTO = new UserDTO(rank,name,password);
            if(userService.verifyUser(userDTO)){
                SideBarFormController.setUSer(name,isAdmin);
                ancPane.getChildren().clear();
                ancPane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/SideBarForm.fxml")));
            }else {
                new Alert(Alert.AlertType.ERROR, "user Name,password wrong or you are not " + rank).show();
            }
        }
        lblError.setText(error);
    }

    public void changeLoginOnAction(MouseEvent mouseEvent) {
        if (isAdmin){
            isAdmin=false;
            lblChange.setText("if you Admin ? click here!");
            lblLogin.setText("Supervisor Login");
        }else {
            isAdmin=true;
            lblChange.setText("if you Supervisor ? click here!");
            lblLogin.setText("Admin Login");
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
