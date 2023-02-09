package lk.ijse.tailorshop.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreenController {


    public AnchorPane ancPane;
    public ImageView imgSlash;
    public Rectangle rctContainer;
    public Rectangle rctLoading;
    public Label lblLoading;

    public void initialize(){
        Image image1 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (1).jpg");
        Image image2 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (2).jpg");
        Image image3 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (3).jpg");
        Image image4 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (4).jpg");
        Image image5 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (5).jpg");
        Image image6 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (6).jpg");
        Image image7 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (7).jpg");
        Image image8 = new Image("lk/ijse/tailorshop/assests/slash/slashImage (8).jpg");


        KeyFrame keyFrame1= new KeyFrame(Duration.millis(0), actionEvent ->{
            imgSlash.setImage(image1);
            lblLoading.setText("Initializing Application....");
            rctLoading.setWidth(rctContainer.getWidth()*0.1);
        });
        KeyFrame keyFrame2= new KeyFrame(Duration.millis(500), actionEvent ->{
            imgSlash.setImage(image2);
            lblLoading.setText("Loading Internal Resources....");
            rctLoading.setWidth(rctContainer.getWidth()*0.2);
        });
        KeyFrame keyFrame3= new KeyFrame(Duration.millis(1000), actionEvent ->{
            imgSlash.setImage(image3);
            lblLoading.setText("Loading Images....");
            rctLoading.setWidth(rctContainer.getWidth()*0.3);
        });
        KeyFrame keyFrame4= new KeyFrame(Duration.millis(1500), actionEvent ->{
            imgSlash.setImage(image4);
            lblLoading.setText("Loading Data....");
            rctLoading.setWidth(rctContainer.getWidth()*0.4);
        });
        KeyFrame keyFrame5= new KeyFrame(Duration.millis(2000), actionEvent ->{
            imgSlash.setImage(image5);
            lblLoading.setText("Loading Logic....");
            rctLoading.setWidth(rctContainer.getWidth()*0.5);

        });
        KeyFrame keyFrame6= new KeyFrame(Duration.millis(3500), actionEvent ->{
            imgSlash.setImage(image6);
            lblLoading.setText("Welcome to Style....");
            rctLoading.setWidth(rctContainer.getWidth()*0.6);
        });
        KeyFrame keyFrame7= new KeyFrame(Duration.millis(4000), actionEvent ->{
            imgSlash.setImage(image7);
            lblLoading.setText("Welcome to UIs....");
            rctLoading.setWidth(rctContainer.getWidth()*0.7);
        });
        KeyFrame keyFrame8= new KeyFrame(Duration.millis(4500), actionEvent ->{
            imgSlash.setImage(image8);
            lblLoading.setText("Welcome to TSMS v1.0.0");
            rctLoading.setWidth(rctContainer.getWidth()*0.8);
        });
        KeyFrame keyFrame9= new KeyFrame(Duration.millis(5000), actionEvent ->{
            lblLoading.setText("Welcome to TSMS v1.0.0");
            rctLoading.setWidth(rctContainer.getWidth()*0.9);
        });
        KeyFrame keyFrame10 = new KeyFrame(Duration.millis(5500),actionEvent -> {
            try {
                rctLoading.setWidth(rctContainer.getWidth());
                ancPane.getChildren().clear();
                ancPane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Timeline timeline = new Timeline(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6,keyFrame7,keyFrame8,keyFrame9,keyFrame10);
        timeline.playFromStart();




    }
}
