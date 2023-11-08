package lk.ijse.controller;

import com.calendarfx.view.CalendarView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.db.DbConnections;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {
    @FXML
    public ChoiceBox problemChoiceBox1;

    @FXML
    public Rectangle doctorRec;

    @FXML
    public TextField doctorChooseTxt;

    @FXML
    private CalendarView appointmentCalanderView;

    @FXML
    private Label appointmentIdLbl;

    @FXML
    private AnchorPane appoitmentPane;

    @FXML
    private ChoiceBox<String> problemChoiceBox;

    @FXML
    private Rectangle problemRec;

    @FXML
    private TextField problemTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setValuesToChoiceBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add a ChangeListener to the ChoiceBox
        problemChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Update the TextField when an option is selected
                problemTxt.setText(newValue);
            }
        });
    }

    private void setValuesToChoiceBox() throws SQLException {
        ObservableList<String> problemChoices = FXCollections.observableArrayList(
                "Buy prescription glass",
                "Check eyes",
                "Option 3"
        );

        problemChoiceBox.setItems(problemChoices);

        problemChoiceBox.setValue(problemChoices.get(0));

        String[][] doctors = DbConnections.getDetails("doctor", 9);
        ObservableList<String> doctorChoises = FXCollections.observableArrayList(
                "Any",
                ""+ doctors[0][0] +"",
                "Option 3"
        );

        problemChoiceBox1.setItems(doctorChoises);

        problemChoiceBox1.setValue(doctorChoises.get(0));
    }

    @FXML
    public void problemChoiceBoxOnAction(MouseEvent event) {
        String selectedProblem = problemChoiceBox.getValue();

        problemTxt.setText(selectedProblem);
    }
}