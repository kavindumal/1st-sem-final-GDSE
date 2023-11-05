package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
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
    private FontIcon transactionIcon;

    @FXML
    private Pane movInPane;

    @FXML
    void employeeIconOnAction(MouseEvent event) {
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(556);
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
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(376);
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
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(197);
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
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(467);
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
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(286);
    }

    @FXML
    void prescriptionIconOnMouseEntered(MouseEvent event) {
        prescriptionIcon.setIconColor(Color.BLUE);
    }

    @FXML
    void prescriptionIconOnMouseExited(MouseEvent event) {
        prescriptionIcon.setIconColor(Color.BLACK);
    }

    @FXML
    void settingIconOnAction(MouseEvent event) {
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(906);
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
        movInPane.setLayoutX(26);
        movInPane.setLayoutY(646);
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

    }
}
