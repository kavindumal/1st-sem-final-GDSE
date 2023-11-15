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

public class SelectGenderFormController implements Initializable {

    @FXML
    private Circle menCircle;

    @FXML
    private Circle womenCircle;

    @FXML
    private AnchorPane genderSelectPane;

    public static int genderSelect = 0;
    @FXML
    void menCircleOnMouseClicked(MouseEvent event) {
        genderSelect = 1;
        womenCircle.setOpacity(0.0);
        menCircle.setOpacity(1.0);
    }

    @FXML
    void menCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void menCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        if (genderSelect != 0) {
            genderSelectPane.getChildren().clear();
            try {
                genderSelectPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFaceShapeForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @FXML
    void womenCircleOnMouseClicked(MouseEvent event) {
        genderSelect = 2;
        menCircle.setOpacity(0.0);
        womenCircle.setOpacity(1.0);
    }

    @FXML
    void womenCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void womenCircleOnMouseExited(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderSelect = 0;
    }
}
