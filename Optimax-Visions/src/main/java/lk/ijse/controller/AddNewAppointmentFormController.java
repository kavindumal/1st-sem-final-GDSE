package lk.ijse.controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXDatePicker;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {
    @FXML
    private AnchorPane appoitmentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GridPane gridPane = new GridPane();
        int numRows = 5; // Number of rows in the calendar grid
        int numCols = 7; // Number of columns in the calendar grid

        // Create a 2D array of Buttons to represent the calendar
        Button[][] dateButtons = new Button[numRows][numCols];
        gridPane.setHgap(10.0);
        gridPane.setVgap(10.0);


        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button button = new Button();
                // Customize the button style and action as needed
                button.setMinSize(60, 60);
                button.setText("1");
                button.setStyle("-fx-background-radius: 20;");
                gridPane.add(button, col, row);
                dateButtons[row][col] = button;
            }
        }

        // Customize the date highlighting (e.g., holidays)
        dateButtons[2][4].setStyle("-fx-base: #ff0000;"); // Highlighting a specific date
        appoitmentPane.getChildren().add(gridPane);
    }
}
