package lk.ijse.controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.layout.GridPane;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {
    @FXML
    private AnchorPane appoitmentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public int dateCount() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
        DayOfWeek startDay = firstDayOfMonth.getDayOfWeek();

        int count = -1;
        if (startDay.equals(DayOfWeek.MONDAY)) count = 0;
        else if (startDay.equals(DayOfWeek.TUESDAY)) count = 1;
        else if (startDay.equals(DayOfWeek.WEDNESDAY)) count = 2;
        else if (startDay.equals(DayOfWeek.THURSDAY)) count = 3;
        else if (startDay.equals(DayOfWeek.FRIDAY)) count = 4;
        else if (startDay.equals(DayOfWeek.SATURDAY)) count = 5;
        else if (startDay.equals(DayOfWeek.SUNDAY)) count = 6;
        System.out.println(count);
        return count;
    }
}
