package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class SelectGenderFormController {

    @FXML
    private Circle menCircle;

    @FXML
    private Circle womenCircle;

    @FXML
    private AnchorPane genderSelectPane;
    @FXML
    void menCircleOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void menCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void menCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        genderSelectPane.getChildren().clear();
        try {
            genderSelectPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFaceShapeForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void womenCircleOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void womenCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void womenCircleOnMouseExited(MouseEvent event) {

    }

}
