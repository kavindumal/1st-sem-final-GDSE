package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SpecialFeaturesFormController implements Initializable {

    @FXML
    private Circle clipCircle;

    @FXML
    private Circle lightweightCircle;

    @FXML
    private Circle noseBridgeCircle;

    @FXML
    private Circle nosePadCircle;

    @FXML
    private Circle springCircle;

    @FXML
    private AnchorPane specialPane;

    public static int specialSelect = 0;

    @FXML
    void clipCircleOnMouseClicked(MouseEvent event) {
        specialSelect = 4;
        lightweightCircle.setOpacity(0);
        noseBridgeCircle.setOpacity(0);
        nosePadCircle.setOpacity(0);
        clipCircle.setOpacity(1);
        springCircle.setOpacity(0);
    }

    @FXML
    void clipCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void clipCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void lightweightCircleOnMouseClicked(MouseEvent event) {
        specialSelect = 1;
        lightweightCircle.setOpacity(1);
        noseBridgeCircle.setOpacity(0);
        nosePadCircle.setOpacity(0);
        clipCircle.setOpacity(0);
        springCircle.setOpacity(0);
    }

    @FXML
    void lightweightCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void lightweightCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void noseBridgeCircleOnMouseClicked(MouseEvent event) {
        specialSelect = 2;
        lightweightCircle.setOpacity(0);
        noseBridgeCircle.setOpacity(1);
        nosePadCircle.setOpacity(0);
        clipCircle.setOpacity(0);
        springCircle.setOpacity(0);
    }

    @FXML
    void noseBridgeCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void noseBridgeCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nosePadCircleOnMouseClicked(MouseEvent event) {
        specialSelect = 3;
        lightweightCircle.setOpacity(0);
        noseBridgeCircle.setOpacity(0);
        nosePadCircle.setOpacity(1);
        clipCircle.setOpacity(0);
        springCircle.setOpacity(0);
    }

    @FXML
    void nosePadCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void nosePadCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void springCircleOnMouseClicked(MouseEvent event) {
        specialSelect = 5;
        lightweightCircle.setOpacity(0);
        noseBridgeCircle.setOpacity(0);
        nosePadCircle.setOpacity(0);
        clipCircle.setOpacity(0);
        springCircle.setOpacity(1);
    }

    @FXML
    void springCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void springCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void viewResultBtnOnAction(ActionEvent event) {
        if (specialSelect!=0) {
            specialPane.getChildren().clear();
            try {
                specialPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/prescriptionResultForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        specialSelect = 0;
    }
}
