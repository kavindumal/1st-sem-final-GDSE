package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DashFormController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane sidebarPane;

    @FXML
    private ImageView employeePageImg;

    @FXML
    private ImageView glassPageImg;

    @FXML
    private ImageView homePageImg;

    @FXML
    private ImageView movingImg;

    @FXML
    private ImageView movingImg1;

    @FXML
    private ImageView movingImg11;

    @FXML
    private ImageView movingImg111;

    @FXML
    private ImageView movingImg1111;

    @FXML
    private ImageView movingImg11111;

    @FXML
    private ImageView movingImg111111;

    @FXML
    private ImageView patientPageImg;

    @FXML
    private ImageView prescriptionGlassPageImg;

    @FXML
    private ImageView settingPageImg;

    @FXML
    private ImageView transactionPageImg;

    @FXML
    void employeePageImgOnAction(MouseEvent event) {
        employeePageImg .setOpacity(1);
    }

    @FXML
    void sliderbarPaneOnMouserEntered(MouseEvent event) {
        sidebarPane.setPrefWidth(310);
    }

    @FXML
    void sliderbarPaneOnMouserExited(MouseEvent event) {
        sidebarPane.setPrefWidth(144);
    }

    @FXML
    void employeePageImgOnMouseEmtered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg1111);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void employeePageImgOnMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg1111);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void glassPageImgMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg11);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void glassPageImgMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg11);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void glassPageImgOnAction(MouseEvent event) {
        glassPageImg.setOpacity(1);
    }

    @FXML
    void homeImgMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void homeImgMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void homePageImgOnAction(MouseEvent event) {
        homePageImg.setOpacity(1);
    }

    @FXML
    void patientPageImgOnAction(MouseEvent event) {
        patientPageImg.setOpacity(1);
    }

    @FXML
    void patientPageImgOnMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg111);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void patientPageImgOnMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg111);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void prescriptionGlassPageImgMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg1);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void prescriptionGlassPageImgMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void prescriptionGlassPageImgOnAction(MouseEvent event) {
        prescriptionGlassPageImg.setOpacity(1);
    }

    @FXML
    void settingPageImgOnAction(MouseEvent event) {
        settingPageImg.setOpacity(1);
    }

    @FXML
    void settingPageImgOnMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg111111);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    @FXML
    void settingPageImgOnMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg111111);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @FXML
    void transactionPageImgOnAction(MouseEvent event) {
        transactionPageImg.setOpacity(1);
    }

    @FXML
    void transactionPageImgOnMouseEntered(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg11111);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    void transactionPageImgOnMouseExited(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), movingImg11111);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homePageImg.setOpacity(1);
        prescriptionGlassPageImg.setOpacity(0.5);
        glassPageImg.setOpacity(0.5);
        patientPageImg.setOpacity(0.5);
        employeePageImg.setOpacity(0.5);
        transactionPageImg.setOpacity(0.5);
        settingPageImg.setOpacity(0.5);
    }
}