package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private AnchorPane bodyPane;

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
    private AnchorPane rootPane;

    @FXML
    private ImageView settingPageImg;

    @FXML
    private ImageView transactionPageImg;

    @FXML
    void employeePageImgOnAction(MouseEvent event) {

    }

    @FXML
    void employeePageImgOnMouseEmtered(MouseEvent event) {

    }

    @FXML
    void employeePageImgOnMouseExited(MouseEvent event) {

    }

    @FXML
    void glassPageImgMouseEntered(MouseEvent event) {

    }

    @FXML
    void glassPageImgMouseExited(MouseEvent event) {

    }

    @FXML
    void glassPageImgOnAction(MouseEvent event) {

    }

    @FXML
    void homeImgMouseEntered(MouseEvent event) {

    }

    @FXML
    void homeImgMouseExited(MouseEvent event) {

    }

    @FXML
    void homePageImgOnAction(MouseEvent event) {

    }

    @FXML
    void patientPageImgOnAction(MouseEvent event) {

    }

    @FXML
    void patientPageImgOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void patientPageImgOnMouseExited(MouseEvent event) {

    }

    @FXML
    void prescriptionGlassPageImgMouseEntered(MouseEvent event) {

    }

    @FXML
    void prescriptionGlassPageImgMouseExited(MouseEvent event) {

    }

    @FXML
    void prescriptionGlassPageImgOnAction(MouseEvent event) {

    }

    @FXML
    void settingPageImgOnAction(MouseEvent event) {

    }

    @FXML
    void settingPageImgOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void settingPageImgOnMouseExited(MouseEvent event) {

    }

    @FXML
    void transactionPageImgOnAction(MouseEvent event) {

    }

    @FXML
    void transactionPageImgOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void transactionPageImgOnMouseExited(MouseEvent event) {

    }

    void fadeInTransition() {

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

        movingImg.setOpacity(1);
    }
}
