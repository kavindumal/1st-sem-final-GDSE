package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class EyeGlassFormController {
    @FXML
    public AnchorPane eyGlassPane;

    @FXML
    private TableColumn<?, ?> PatientNameCol;

    @FXML
    private TableColumn<?, ?> PatientTypeCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> deleteRecordCol;

    @FXML
    private JFXButton frameDetailsBtn;

    @FXML
    private TableColumn<?, ?> glassIdCol;

    @FXML
    private JFXButton lenseDetailsBtn;

    @FXML
    private TableView<?> orderedGlassesTbl;

    @FXML
    private TableColumn<?, ?> timeCol;

    @FXML
    void frameDetailsBtnOnAction(ActionEvent event) {
        eyGlassPane.getChildren().clear();
        try {
            eyGlassPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/frameDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lenseDetailsBtnOnAction(ActionEvent event) {
        eyGlassPane.getChildren().clear();
        try {
            eyGlassPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/lensesDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
