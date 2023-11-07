package lk.ijse.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    @FXML
    private FontIcon employeeIcon;

    @FXML
    private FontIcon eyeglassIcon;

    @FXML
    private FontIcon homeIcon;

    @FXML
    private FontIcon patientIcon;

    @FXML
    private FontIcon prescriptionIcon;

    @FXML
    private FontIcon settingIcon;

    @FXML
    private FontIcon sideBarIcon;

    @FXML
    private AnchorPane sideBarPane;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private AnchorPane bodyPane;

    @FXML
    private FontIcon transactionIcon;

    @FXML
    private Pane movInPane;

    public ImageView popupsImg = new ImageView("img/icons/Union.png");

    public ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), popupsImg);
    public TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), popupsImg);

    int sideBar = 0;

    int count = 0;

    @FXML
    void employeeIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(556);
        count = 6;
    }

    @FXML
    void employeeIconOnMouseEntered(MouseEvent event) {
        employeeIcon.setIconColor(Color.BLUE);
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(479);
    }

    @FXML
    void employeeIconOnMouseExited(MouseEvent event) {
        employeeIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @FXML
    void eyeglassIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(376);
        count = 4;
    }

    @FXML
    void eyeglassIconOnMouseEntered(MouseEvent event) {
        eyeglassIcon.setIconColor(Color.BLUE);
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(300);
    }

    @FXML
    void eyeglassIconOnMouseExited(MouseEvent event) {
        eyeglassIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @FXML
    void homeIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(197);
        count = 2;
    }

    void checkMoveInPaneLocation() {
        if (count == 2) homeIcon.setIconColor(Color.BLACK);
        else if (count == 3) prescriptionIcon.setIconColor(Color.BLACK);
        else if (count == 4) eyeglassIcon.setIconColor(Color.BLACK);
        else if (count == 5) patientIcon.setIconColor(Color.BLACK);
        else if (count == 6) employeeIcon.setIconColor(Color.BLACK);
        else if (count == 7) transactionIcon.setIconColor(Color.BLACK);
        else if (count == 8) settingIcon.setIconColor(Color.BLACK);
    }
    @FXML
    void homeIconOnMouseEntered(MouseEvent event) {
        homeIcon.setIconColor(Color.BLUE);
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(119);
    }

    @FXML
    void homeIconOnMouseExited(MouseEvent event) {
        homeIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @FXML
    void patientIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(467);
        count = 5;
    }

    @FXML
    void patientIconOnMouseEntered(MouseEvent event) {
        patientIcon.setIconColor(Color.BLUE);
        //        popupsImg.setTranslateY(119);
//        translateTransition.setToY(209);
//        translateTransition.play();
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(386);
    }

    @FXML
    void patientIconOnMouseExited(MouseEvent event) {
        patientIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @FXML
    void prescriptionIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(286);
        count = 3;
    }

    @FXML
    void prescriptionIconOnMouseEntered(MouseEvent event) {
        prescriptionIcon.setIconColor(Color.BLUE);
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(209);
    }

    @FXML
    void prescriptionIconOnMouseExited(MouseEvent event) {
        prescriptionIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @FXML
    void settingIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(906);
        count = 8;
    }

    @FXML
    void settingIconOnMouseEntered(MouseEvent event) {
        settingIcon.setIconColor(Color.BLUE);
    }

    @FXML
    void settingIconOnMouseExited(MouseEvent event) {
        settingIcon.setIconColor(Color.BLACK);
    }

    @FXML
    void sideBarIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        sideBar++;
        if (sideBar % 2 == 1) sideBarPane.setPrefWidth(280);
        else sideBarPane.setPrefWidth(101);
        count = 1;
    }

    @FXML
    void sideBarIconOnMouseAction(MouseEvent event) {
        sideBarIcon.setIconColor(Color.BLUE);
    }

    @FXML
    void sideBarIconOnMouseExited(MouseEvent event) {
        sideBarIcon.setIconColor(Color.BLACK);
    }

    @FXML
    void transactionIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(646);
        count = 7;
    }

    @FXML
    void transactionIconOnMouseEntered(MouseEvent event) {
        transactionIcon.setIconColor(Color.BLUE);
        scaleTransition.play();
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setLayoutY(570);
    }

    @FXML
    void transactionIconOnMouseExited(MouseEvent event) {
        transactionIcon.setIconColor(Color.BLACK);
        bodyPane.getChildren().remove(popupsImg);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUi();
        setTransitionFirstLog();
        popupsImg.setFitWidth(216);
        popupsImg.setFitHeight(223);
        popupsImg.setLayoutX(-10);
    }

    private void loadUi() {
        count = 2;
        movInPane.setOpacity(1);
    }

    private void setTransitionFirstLog() {
        popupsImg.setScaleX(0.1);
        popupsImg.setScaleY(0.1);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
    }
}