package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    private ImageView popupsImg = new ImageView("img/icons/Union.png");
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
    }

    @FXML
    void employeeIconOnMouseExited(MouseEvent event) {
        employeeIcon.setIconColor(Color.BLACK);
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
    }

    @FXML
    void eyeglassIconOnMouseExited(MouseEvent event) {
        eyeglassIcon.setIconColor(Color.BLACK);
    }

    @FXML
    void homeIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(197);
        homeIcon.setIconColor(Color.BLUE);
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
    }

    @FXML
    void homeIconOnMouseExited(MouseEvent event) {
        homeIcon.setIconColor(Color.BLACK);
    }

    @FXML
    void patientIconOnAction(MouseEvent event) {
        checkMoveInPaneLocation();
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(467);
        patientIcon.setIconColor(Color.BLUE);
        count = 5;
    }

    @FXML
    void patientIconOnMouseEntered(MouseEvent event) {
        patientIcon.setIconColor(Color.BLUE);
    }

    @FXML
    void patientIconOnMouseExited(MouseEvent event) {
        patientIcon.setIconColor(Color.BLACK);
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
        bodyPane.getChildren().add(popupsImg);
        popupsImg.setFitWidth(216);
        popupsImg.setFitHeight(223);
        popupsImg.setLayoutX(-10);
        popupsImg.setLayoutY(220);
    }

    @FXML
    void prescriptionIconOnMouseExited(MouseEvent event) {
        bodyPane.getChildren().remove(popupsImg);
        prescriptionIcon.setIconColor(Color.BLACK);
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
    }

    @FXML
    void transactionIconOnMouseExited(MouseEvent event) {
        transactionIcon.setIconColor(Color.BLACK);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUi();
    }

    private void loadUi() {
        count = 2;
        homeIcon.setIconColor(Color.BLUE);
        movInPane.setOpacity(1);
    }
}