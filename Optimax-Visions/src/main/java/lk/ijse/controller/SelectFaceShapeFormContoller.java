package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Objects;

public class SelectFaceShapeFormContoller {

    @FXML
    private Circle diamondCircle;

    @FXML
    private Circle heartCircle;

    @FXML
    private Circle ovalCircle;

    @FXML
    private Circle roundCircle;

    @FXML
    private Circle squareCircle;

    @FXML
    private Circle triangleCircle;

    @FXML
    private Circle unsureCircle;

    @FXML
    private AnchorPane faceShapePane;

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
        faceShapePane.getChildren().clear();
        try {
            faceShapePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFavoriteForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
