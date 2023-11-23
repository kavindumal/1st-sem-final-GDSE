package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
        ObservableList<AppointmentDto> last5Appointment = getLast5Appointment();
        int y = 160;
        for (int i = 0; i < last5Appointment.size(); i++) {
            Pane paneMain = new Pane();
            Pane paneRuck = new Pane();
            FontIcon icon = new FontIcon("bi-person-circle");
            icon.setIconColor(Color.WHITE);
            icon.setIconSize(25);
            icon.setLayoutX(27);
            icon.setLayoutY(54);
            paneRuck.setPrefWidth(78);
            paneRuck.setPrefHeight(87);
            paneRuck.setLayoutX(1357);
            paneRuck.setLayoutY(y);
            Label label = new Label();
            Label label1 = new Label();
            label1.setText(String.valueOf(last5Appointment.get(i).getTime()));
            label1.setLayoutX(90);
            label1.setLayoutY(40);
//            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    updateTimer(label1);
//                }
//            }));

//            timeline.setCycleCount(Timeline.INDEFINITE);
//            timeline.play();
            label1.setText(String.valueOf(last5Appointment.get(i).getTime()));
            label1.setStyle("-fx-font-size: 19px;-fx-text-fill: blue");
            label.setText(last5Appointment.get(i).getId());
            label.setLayoutX(90);
            label.setLayoutY(20);
            label.setStyle("-fx-font-weight: bold; -fx-font-size: 20px");
            paneMain.setLayoutX(1359);
            paneMain.setLayoutY(y);
            paneMain.setPrefHeight(85);
            paneMain.setPrefWidth(256);
            paneMain.setStyle("-fx-background-color: rgba(255,255,255,0.63); -fx-background-radius: 30;");
            paneRuck.setStyle("-fx-background-color: rgb(45,45,201); -fx-background-radius: 30;");
            paneMain.getChildren().add(label);
            paneMain.getChildren().add(label1);
            homePane.getChildren().add(paneMain);
            homePane.getChildren().add(paneRuck);
            paneRuck.getChildren().add(icon);
            y = y + 120;
        }
    }

//    private void updateTimer(Label label1) {
//        LocalTime currentTime = LocalTime.now();
//        Duration duration = Duration.between(currentTime, LocalTime.parse(label1.getText()));
//        label1.setText(String.valueOf(duration));
//    }

    @SneakyThrows
    private ObservableList<AppointmentDto> getLast5Appointment() {
        AddNewAppointmentModel model = new AddNewAppointmentModel();
        String[][] last5Appointments = model.getResentAppointments();

        ObservableList<AppointmentDto> obList = FXCollections.observableArrayList();
        LocalDate nowDate = LocalDate.now();
        for (int i = 0; i < last5Appointments.length; i++) {
            if (last5Appointments[i][2].equals(nowDate.toString())) {
                LocalTime parsedTime = parseTime(last5Appointments[i][1]);
                var tm = new AppointmentDto(last5Appointments[i][0], parsedTime, LocalDate.parse(last5Appointments[i][2]), last5Appointments[i][3], last5Appointments[i][4], last5Appointments[i][5], last5Appointments[i][6], last5Appointments[i][7]);
                obList.add(tm);
            }
        }

        ObservableList<AppointmentDto> obListNew = FXCollections.observableArrayList();
        for (int i = 0; i < obList.size(); i++) {
            LocalTime time = LocalTime.now();
            if (obList.get(i).getTime().isBefore(time)) {
                time = obList.get(0).getTime();
                obListNew.add(obList.get(i));
                if (obListNew.size() == 5) {
                    break;
                }
            }
        }
        return obListNew;
    }

    private LocalTime parseTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");

        return LocalTime.parse(s, formatter);
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
