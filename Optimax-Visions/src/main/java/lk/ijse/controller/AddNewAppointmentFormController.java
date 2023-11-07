package lk.ijse.controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {
    @FXML
    private AnchorPane appoitmentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Calendar calendar = Calendar.getInstance();
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int count = dateCount();

        GridPane gridPane = new GridPane();
        int numRows = 5; // Number of rows in the calendar grid
        int numCols = 7; // Number of columns in the calendar grid
        int remainRow = (numCols * numRows) - daysInMonth;

        // Create a 2D array of Buttons to represent the calendar
        Button[][] dateButtons = new Button[numRows][numCols];
        gridPane.setHgap(10.0);
        gridPane.setVgap(10.0);

        int dayCount = 1; // Initialize the day count

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (row * numCols + col >= remainRow) {
                    Button button = new Button();
                    // Customize the button style and action as needed
                    button.setMinSize(60, 60);
                    button.setText(String.valueOf(dayCount)); // Set the button text to the day
                    button.setStyle("-fx-background-color: transparent;");
                    gridPane.add(button, col, row);
                    dateButtons[row][col] = button;

                    dayCount++; // Increment the day count
                }
            }
        }

        appoitmentPane.getChildren().add(gridPane);
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
