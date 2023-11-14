package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class SelectGenderFormController {

    @FXML
    private Circle menCircle;

    @FXML
    private Circle womenCircle;

    @FXML
    void menCircleOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void menCircleOnMouseEntered(MouseEvent event) {
        menCircle.setOpacity(1.0);
    }

    @FXML
    void menCircleOnMouseExited(MouseEvent event) {
        menCircle.setOpacity(0);
    }

    @FXML
    void womenCircleOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void womenCircleOnMouseEntered(MouseEvent event) {
        womenCircle.setOpacity(1.0);
    }

    @FXML
    void womenCircleOnMouseExited(MouseEvent event) {
        womenCircle.setOpacity(0);
    }

}
