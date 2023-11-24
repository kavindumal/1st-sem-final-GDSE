package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.dto.AppointmentDto;
import lk.ijse.model.AddNewAppointmentModel;
import lombok.SneakyThrows;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @FXML
    private Pane appointmentPane;


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
