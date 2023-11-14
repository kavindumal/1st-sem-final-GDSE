package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class SelectColorFormController {

    @FXML
    private AnchorPane colorFramePane;

    @FXML
    private Circle heartCircle;

    @FXML
    private Circle ovalCircle;

    @FXML
    private Circle ovalCircle1;

    @FXML
    private Circle ovalCircle11;

    @FXML
    private Circle ovalCircle111;

    @FXML
    private Circle triangleCircle;

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
        colorFramePane.getChildren().clear();
        try {
            colorFramePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFaceShapeForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
