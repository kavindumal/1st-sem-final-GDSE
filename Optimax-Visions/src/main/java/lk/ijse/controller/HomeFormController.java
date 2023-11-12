package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class HomeFormController {

    @FXML
    private JFXButton newAppointmentBtn;

    @FXML
    private AnchorPane homePane;

    public void newAppointmentBtnOnAction(ActionEvent actionEvent) {
        homePane.getChildren().clear();
        try {
            homePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/addNewAppointmentForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPrescriptionBtnOnAction(ActionEvent actionEvent) {
        homePane.getChildren().clear();
        try {
            homePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/prescriptionDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
