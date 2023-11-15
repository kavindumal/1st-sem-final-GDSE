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

    public static int choice = 0;
    @FXML
    void menCircleOnMouseClicked(MouseEvent event) {
        choice = 1;
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
        if (choice == 0) {

        } else {
            genderSelectPane.getChildren().clear();
            try {
                genderSelectPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectFaceShapeForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void womenCircleOnMouseClicked(MouseEvent event) {
        choice = 2;
        menCircle.setOpacity(0.0);
        womenCircle.setOpacity(1.0);
    }

    @FXML
    void womenCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void womenCircleOnMouseExited(MouseEvent event) {

    }

}
