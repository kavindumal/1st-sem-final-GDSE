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

public class SelectColorFormController implements Initializable {

    @FXML
    private Circle blackCircle;

    @FXML
    private AnchorPane colorFramePane;

    @FXML
    private Circle colorfulCircle;

    @FXML
    private Circle neutralCircle;

    @FXML
    private Circle patternCircle;

    @FXML
    private Circle tortoiseCircle;

    @FXML
    private Circle translucentCircle;

    public static int colorSelect = 0;

    @FXML
    void blackCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 1;
        blackCircle.setOpacity(1.0);
        tortoiseCircle.setOpacity(0.0);
        patternCircle.setOpacity(0.0);
        neutralCircle.setOpacity(0.0);
        colorfulCircle.setOpacity(0.0);
        translucentCircle.setOpacity(0.0);
    }

    @FXML
    void blackCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void blackCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void colorfulCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 5;
        blackCircle.setOpacity(0.0);
        tortoiseCircle.setOpacity(0.0);
        patternCircle.setOpacity(0.0);
        neutralCircle.setOpacity(0.0);
        colorfulCircle.setOpacity(1.0);
        translucentCircle.setOpacity(0.0);
    }

    @FXML
    void colorfulCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void colorfulCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void neutralCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 4;
        blackCircle.setOpacity(0.0);
        tortoiseCircle.setOpacity(0.0);
        patternCircle.setOpacity(0.0);
        neutralCircle.setOpacity(1.0);
        colorfulCircle.setOpacity(0.0);
        translucentCircle.setOpacity(0.0);
    }

    @FXML
    void neutralCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void neutralCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        if (colorSelect!=0) {
            colorFramePane.getChildren().clear();
            try {
                colorFramePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectMaterialForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @FXML
    void patternCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 3;
        blackCircle.setOpacity(0.0);
        tortoiseCircle.setOpacity(0.0);
        patternCircle.setOpacity(1.0);
        neutralCircle.setOpacity(0.0);
        colorfulCircle.setOpacity(0.0);
        translucentCircle.setOpacity(0.0);
    }

    @FXML
    void patternCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void patternCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void tortoiseCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 2;
        blackCircle.setOpacity(0.0);
        tortoiseCircle.setOpacity(1.0);
        patternCircle.setOpacity(0.0);
        neutralCircle.setOpacity(0.0);
        colorfulCircle.setOpacity(0.0);
        translucentCircle.setOpacity(0.0);
    }

    @FXML
    void tortoiseCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tortoiseCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void translucentCircleOnMouseClicked(MouseEvent event) {
        colorSelect = 6;
        blackCircle.setOpacity(0.0);
        tortoiseCircle.setOpacity(0.0);
        patternCircle.setOpacity(0.0);
        neutralCircle.setOpacity(0.0);
        colorfulCircle.setOpacity(0.0);
        translucentCircle.setOpacity(1.0);
    }

    @FXML
    void translucentCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void translucentCircleOnMouseExited(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorSelect = 0;
    }
}
