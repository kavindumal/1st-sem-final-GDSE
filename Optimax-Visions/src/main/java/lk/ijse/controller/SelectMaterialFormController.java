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

public class SelectMaterialFormController implements Initializable {

    @FXML
    private Circle ecoCircle;

    @FXML
    private AnchorPane materialPane;

    @FXML
    private Circle metalCircle;

    @FXML
    private Circle mixedCircle;

    @FXML
    private Circle plasticCircle;

    public static int materialSelect = 0;

    @FXML
    void ecoCircleOnMouseClicked(MouseEvent event) {
        materialSelect = 4;
        metalCircle.setOpacity(0);
        plasticCircle.setOpacity(0);
        mixedCircle.setOpacity(0);
        ecoCircle.setOpacity(1);
    }

    @FXML
    void ecoCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void ecoCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void metalCircleOnMouseClicked(MouseEvent event) {
        materialSelect = 1;
        metalCircle.setOpacity(1);
        plasticCircle.setOpacity(0);
        mixedCircle.setOpacity(0);
        ecoCircle.setOpacity(0);
    }

    @FXML
    void metalCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void metalCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void mixedCircleOnMouseClicked(MouseEvent event) {
        materialSelect = 3;
        metalCircle.setOpacity(0);
        plasticCircle.setOpacity(0);
        mixedCircle.setOpacity(1);
        ecoCircle.setOpacity(0);
    }

    @FXML
    void mixedCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void mixedCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        if (materialSelect!=0) {
            materialPane.getChildren().clear();
            try {
                materialPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectMeasurementForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @FXML
    void plasticCircleOnMouseClicked(MouseEvent event) {
        materialSelect = 2;
        metalCircle.setOpacity(0);
        plasticCircle.setOpacity(1);
        mixedCircle.setOpacity(0);
        ecoCircle.setOpacity(0);
    }

    @FXML
    void plasticCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void plasticCircleOnMouseExited(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        materialSelect = 0;
    }
}
