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

public class SelectFavoriteFormController implements Initializable {

    @FXML
    private Circle aviatorCircle;

    @FXML
    private Circle browlineCircle;

    @FXML
    private Circle catCircle;

    @FXML
    private AnchorPane frameSelectPane;

    @FXML
    private Circle roundCircle;

    @FXML
    private Circle specialCircle;

    @FXML
    private Circle squreCircle;
    public static int frameSelect = 0;

    @FXML
    void aviatorCircleOnMouseClicked(MouseEvent event) {
        frameSelect = 5;
        browlineCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squreCircle.setOpacity(0.0);
        catCircle.setOpacity(0.0);
        aviatorCircle.setOpacity(1.0);
        specialCircle.setOpacity(0.0);
    }

    @FXML
    void aviatorCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void aviatorCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void browlineCircleOnMouseClicked(MouseEvent event) {
        frameSelect = 1;
        browlineCircle.setOpacity(1.0);
        roundCircle.setOpacity(0.0);
        squreCircle.setOpacity(0.0);
        catCircle.setOpacity(0.0);
        aviatorCircle.setOpacity(0.0);
        specialCircle.setOpacity(0.0);
    }

    @FXML
    void browlineCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void browlineCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void catCircleOnMouseClicked(MouseEvent event) {
        frameSelect = 4;
        browlineCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squreCircle.setOpacity(0.0);
        catCircle.setOpacity(1.0);
        aviatorCircle.setOpacity(0.0);
        specialCircle.setOpacity(0.0);
    }

    @FXML
    void catCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void catCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void nextBtnOnAction(ActionEvent event) {
        if (frameSelect!=0) {
            frameSelectPane.getChildren().clear();
            try {
                frameSelectPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectColorForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {

        }
    }

    @FXML
    void roundCircleOnMOuseClicked(MouseEvent event) {
        frameSelect = 2;
        browlineCircle.setOpacity(0.0);
        roundCircle.setOpacity(1.0);
        squreCircle.setOpacity(0.0);
        catCircle.setOpacity(0.0);
        aviatorCircle.setOpacity(0.0);
        specialCircle.setOpacity(0.0);
    }

    @FXML
    void roundCircleOnMOuseEntered(MouseEvent event) {

    }

    @FXML
    void roundCircleOnMOuseExited(MouseEvent event) {

    }

    @FXML
    void specialCircleOnMouseClicked(MouseEvent event) {
        frameSelect = 6;
        browlineCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squreCircle.setOpacity(0.0);
        catCircle.setOpacity(0.0);
        aviatorCircle.setOpacity(0.0);
        specialCircle.setOpacity(1.0);
    }

    @FXML
    void specialCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void specialCircleOnMouseExited(MouseEvent event) {

    }

    @FXML
    void squreCircleOnMouseClicked(MouseEvent event) {
        frameSelect = 3;
        browlineCircle.setOpacity(0.0);
        roundCircle.setOpacity(0.0);
        squreCircle.setOpacity(1.0);
        catCircle.setOpacity(0.0);
        aviatorCircle.setOpacity(0.0);
        specialCircle.setOpacity(0.0);
    }

    @FXML
    void squreCircleOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void squreCircleOnMouseExited(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        frameSelect = 0;
    }
}
