package lk.ijse.controller;

import com.calendarfx.view.CalendarView;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.db.DbConnections;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {

    @FXML
    public Rectangle doctorRec;

    @FXML
    public TextField doctorChooseTxt;

    @FXML
    public TextField prescriptionTxt;

    @FXML
    public JFXButton confirmBtn;

    @FXML
    public PrefixSelectionComboBox problemComboBox;

    @FXML
    public PrefixSelectionComboBox doctorComboBox;

    @FXML
    public PrefixSelectionComboBox prescriptionComboBox;


    @FXML
    private CalendarView appointmentCalanderView;

    @FXML
    private Label appointmentIdLbl;

    @FXML
    private AnchorPane appoitmentPane;

    @FXML
    private Rectangle problemRec;

    @FXML
    private TextField problemTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setValuesToComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        problemTxt.setText(String.valueOf(problemComboBox.getValue()));
        doctorChooseTxt.setText(String.valueOf(doctorComboBox.getValue()));
        prescriptionTxt.setText(String.valueOf(prescriptionComboBox.getValue()));
        problemComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                problemTxt.setText(newValue);
            }
        });
    }

    private void setValuesToComboBox() throws SQLException {
        ObservableList<String> problemChoices = FXCollections.observableArrayList(
                "\t Buy prescription glass",
                "\t Check eyes",
                "\t Option 3"
        );

        problemComboBox.setItems(problemChoices);
        problemComboBox.setValue(problemChoices.get(0));

        String[][] doctors = DbConnections.getDetails("doctor", 9);
        ObservableList<String> doctorChoiceBox = FXCollections.observableArrayList();
        for (int i = 0; i < doctors.length; i++) {
            if (i == 0) {
                doctorChoiceBox.add("\t Any");
            }
            doctorChoiceBox.add("\t Dr. " + doctors[i][1]);
        };

        doctorComboBox.setItems(doctorChoiceBox);
        doctorComboBox.setValue(doctorChoiceBox.get(0));

        ObservableList<String> prescriptionChoises = FXCollections.observableArrayList(
                "\t Yes he/she have prescription",
                "\t No, he/she don't have prescription"
        );

        prescriptionComboBox.setItems(prescriptionChoises);
        prescriptionComboBox.setValue(prescriptionChoises.get(0));
    }

    @FXML
    public void confirmBtnOnAction(ActionEvent actionEvent) {

    }
}