package lk.ijse.controller;

import com.calendarfx.view.YearMonthView;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.AddNewAppointmentDto;
import lk.ijse.model.AddNewAppointmentModel;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewAppointmentFormController implements Initializable {
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
    public YearMonthView calanderYearMonthView;
    @FXML
    public JFXButton start8Btn,end8Btn,start9Btn,end9Btn,start10Btn,end10Btn,start11Btn,end11Btn,start1Btn,end1Btn,start2Btn,end2Btn,start3Btn,end3Btn,start4Btn,end4Btn;
    public Label timeNotFoundLbl;
    public ImageView backImg;

    @FXML
    private Label appointmentIdLbl;

    @FXML
    private AnchorPane appoitmentPane;

    @FXML
    private TextField problemTxt;

    private JFXButton selectedButton = null;
    String[][] appoitmentArray;
    AddNewAppointmentModel addNewAppointmentModel = new AddNewAppointmentModel();
    int clickedCount = 0;
    String dateGet;

    private void setScheduleTime(String[][] appointments) throws SQLException {
        JFXButton[] appointmentButtons = new JFXButton[]{
                start8Btn, end8Btn, start9Btn, end9Btn, start10Btn, end10Btn, start11Btn, end11Btn,
                start1Btn, end1Btn, start2Btn, end2Btn, start3Btn, end3Btn, start4Btn, end4Btn
        };

        for (int i = 0; i < appointmentButtons.length; i++) {
            appointmentButtons[i].setStyle("-fx-background-color: transparent; -fx-border-color: black; -fx-background-radius: 30; -fx-border-width: 0.5; -fx-border-radius: 30; -fx-text-fill: black");
            appointmentButtons[i].setDisable(false);
        }

        for (int i = 0; i < appointments.length; i++) {
            String appointmentTime = appointments[i][1];
            for (int j = 0; j < appointmentButtons.length; j++) {
                String time = appointmentButtons[j].getText().substring(0, 5);
                if (time.equals(appointmentTime)) {
                    appointmentButtons[j].setStyle("-fx-background-color: #EF4B3C; -fx-border-color: transparent; -fx-background-radius: 30; -fx-text-fill: white");
                    appointmentButtons[j].setDisable(true);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAppointmentButtonHandlers();
        try {
            appointmentIdLbl.setText((addNewAppointmentModel.findNextAppoitmentId().substring(0,addNewAppointmentModel.findNextAppoitmentId().length() - 1)) +(Integer.parseInt(addNewAppointmentModel.findNextAppoitmentId().substring(addNewAppointmentModel.findNextAppoitmentId().length()-1)) + 1));
            appoitmentArray = addNewAppointmentModel.getEqualDateAppoitments(calanderYearMonthView.getDate().toString().replace("[", "").replace("]", ""));
            setScheduleTime(appoitmentArray);
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

        doctorComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                doctorChooseTxt.setText(newValue);
            }
        });

        prescriptionComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                prescriptionTxt.setText(newValue);
                newV = newValue;
            }
        });
    }
    public String newV = " No, he/she don't have prescription";

    private void addAppointmentButtonHandlers() {
        JFXButton[] appointmentButtons = new JFXButton[]{
                start8Btn, end8Btn, start9Btn, end9Btn, start10Btn, end10Btn, start11Btn, end11Btn,
                start1Btn, end1Btn, start2Btn, end2Btn, start3Btn, end3Btn, start4Btn, end4Btn
        };

        for (JFXButton button : appointmentButtons) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    dateGet = button.getText().substring(0, 5);
                    handleAppointmentButtonClick(button);
                }
            });
        }
    }

    private void handleAppointmentButtonClick(JFXButton clickedButton) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 30; -fx-border-radius: 30; -fx-border-color: black; -fx-border-width: 0.5");
        }
        timeNotFoundLbl.setOpacity(0.0);
        clickedButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 30;");
        clickedCount++;
        selectedButton = clickedButton;
    }

    private void setValuesToComboBox() throws SQLException {
        ObservableList<String> problemChoices = FXCollections.observableArrayList(
                "\tBuy prescription glass",
                "\tCheck eyes",
                "\tOption 3"
        );

        problemComboBox.setItems(problemChoices);
        problemComboBox.setValue(problemChoices.get(0));

        String[][] doctors = DbConnections.getDetails("doctor", 7);
        ObservableList<String> doctorChoiceBox = FXCollections.observableArrayList();
        for (int i = 0; i < doctors.length; i++) {
            if (i == 0) {
                doctorChoiceBox.add("\t Any");
            }
            doctorChoiceBox.add("\t Dr. " + doctors[i][1]);
        }

        doctorComboBox.setItems(doctorChoiceBox);
        doctorComboBox.setValue(doctorChoiceBox.get(0));

        ObservableList<String> prescriptionChoises = FXCollections.observableArrayList(
                "\t Yes he/she have prescription",
                "\t No, he/she don't have prescription"
        );

        prescriptionComboBox.setItems(prescriptionChoises);
        prescriptionComboBox.setValue(prescriptionChoises.get(1));
    }

    public ObservableList<String> newObservable;

    @FXML
    public void confirmBtnOnAction(ActionEvent actionEvent) throws IOException {
        AlertSound alertSound = new AlertSound();
        if (clickedCount > 0) {
            appoitmentPane.getChildren().clear();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/patientForm.fxml"));
                Parent root = loader.load();
                PatientFormController controller = loader.getController();
                controller.setValues(new AddNewAppointmentDto(appointmentIdLbl.getText(), calanderYearMonthView.getSelectedDates().toString().replace("[", "").replace("]", ""), dateGet, problemTxt.getText(), doctorChooseTxt.getText(), newV.equals("\t Yes he/she have prescription") ? "yes" : "no"));

                appoitmentPane.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            timeNotFoundLbl.setOpacity(1);
        }
    }

    @FXML
    public void calanderYearMonthViewOnMouseClicked(MouseEvent event) throws SQLException {
        appoitmentArray = addNewAppointmentModel.getEqualDateAppoitments(calanderYearMonthView.getSelectedDates().toString().replace("[", "").replace("]", ""));
        setScheduleTime(appoitmentArray);
    }

    public void backOnAction(MouseEvent event) {

    }
}