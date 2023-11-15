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

public class SelectFaceShapeFormContoller implements Initializable {

    @FXML
    private Circle diamondCircle;

    @FXML
    private AnchorPane faceShapePane;

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
    public static int faceSelect = 0;

    @FXML
    void diamondCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 6;
        diamondCircle.setOpacity(1.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void diamondCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void diamondCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void heartCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 5;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(1.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void heartCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void heartCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        if (faceSelect!=0) {
            faceShapePane.getChildren().clear();
            try {
                faceShapePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFavoriteForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @FXML
    void ovalCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 2;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(1.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void ovalCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void ovalCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void roundCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 4;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(1.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void roundCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void roundCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void squareCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 3;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(1.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void squareCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void squareCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void triangleCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 1;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(1.0);
        unsureCircle.setOpacity(0.0);
    }

    @FXML
    void triangleCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void triangleCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void unsureCircleOnMouseClicked(MouseEvent event) {
        faceSelect = 7;
        diamondCircle.setOpacity(0.0);
        heartCircle.setOpacity(0.0);
        ovalCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squareCircle.setOpacity(0.0);
        triangleCircle.setOpacity(0.0);
        unsureCircle.setOpacity(1.0);
    }

    @FXML
    void unsureCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void unsureCircleOnMouseExited(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        faceSelect = 0;
    }

}
