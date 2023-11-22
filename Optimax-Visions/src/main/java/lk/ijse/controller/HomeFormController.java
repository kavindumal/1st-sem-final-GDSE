package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeFormController implements Initializable {

    @FXML
    private Label timeLbl;

    @FXML
    private Label goodStatusLbl;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setTimeForLbl();
            }
        };
        timer.start();
    }

    private void setTimeForLbl() {
        java.time.LocalTime currentTime = java.time.LocalTime.now();

        String formattedTime = currentTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        String timeOfDay;
        int hour = currentTime.getHour();
        if (hour < 12) {
            timeOfDay = "Morning";
        } else if (hour >= 12 && hour < 16) {
            timeOfDay = "Afternoon";
        } else if (hour >= 16 && hour < 18){
            timeOfDay = "Evening";
        } else {
            timeOfDay = "Night";
        }

        // Update the Label with the formatted time
        timeLbl.setText(formattedTime);
        goodStatusLbl.setText("Good " + timeOfDay + "," + "kavindu");
    }
}
