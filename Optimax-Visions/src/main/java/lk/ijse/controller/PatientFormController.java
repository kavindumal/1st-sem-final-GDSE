package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lk.ijse.dto.AddNewAppointmentDto;
import lk.ijse.dto.PatientDto;
import lk.ijse.model.PatientModel;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientFormController implements Initializable {

    @FXML
    private Rectangle addressRec;

    @FXML
    private TextField addressTxt;

    @FXML
    private JFXButton confirmBtn,checkBtn;

    @FXML
    private Rectangle emailRec;

    @FXML
    private TextField emailTxt;

    @FXML
    private Rectangle familyNameRec;

    @FXML
    private TextField familyNameTxt;

    @FXML
    private Rectangle fullNameRec;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private Rectangle patientIdRec;

    @FXML
    private TextField patientIdTxt;

    @FXML
    private AnchorPane patientPane;

    @FXML
    private Rectangle telRec;

    @FXML
    private TextField telTxt;

    @FXML
    private PrefixSelectionComboBox<String> profileComboBox;
    public PatientModel model = new PatientModel();
    public PatientDto patientDetails;
    public AddNewAppointmentDto appointmentDto;
    public String checkConfirmation;

    @FXML
    void confirmBtnOnAction(ActionEvent event) throws SQLException {
        if (checkConfirmation.equals("\tYes")) {
            if (model.setPaymentDetails()) {
                if (model.setValuestoDatabase(appointmentDto, new PatientDto(patientIdTxt.getText(), fullNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), familyNameTxt.getText(), telTxt.getText()))) {
                    patientPane.getChildren().clear();
                    try {
                        patientPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/homeForm.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            if (model.setPatientDetailsToDatabase(new PatientDto(patientIdTxt.getText(), fullNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), familyNameTxt.getText(), telTxt.getText()))) {
                if (model.setPaymentDetails()){
                    if (model.setAppointmentDetailstoDatabase(appointmentDto, new PatientDto(patientIdTxt.getText(), fullNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), familyNameTxt.getText(), telTxt.getText()))) {
                        patientPane.getChildren().clear();
                        try {
                            patientPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/homeForm.fxml"))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> profileChoices = FXCollections.observableArrayList(
                "\tYes",
                "\tNo"
        );

        profileComboBox.setItems(profileChoices);
        profileComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkConfirmation = newValue;
                if (profileChoices.get(0).equals(newValue)) {
                    patientIdRec.requestFocus();
                    patientIdTxt.requestFocus();
                } else if (profileChoices.get(1).equals(newValue)){
                    try {
                        patientIdTxt.setText(model.getNewPatientId());
                        fullNameRec.requestFocus();
                        fullNameTxt.requestFocus();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    public void checkBtnOnAction(ActionEvent actionEvent) throws SQLException {
        if (model.checkAvailable(new PatientDto(patientIdTxt.getText(), fullNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), familyNameTxt.getText(), telTxt.getText()))) {
            patientDetails = model.getPatientDetails(new PatientDto(patientIdTxt.getText(), fullNameTxt.getText(), addressTxt.getText(), emailTxt.getText(), familyNameTxt.getText(), telTxt.getText()));

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), checkBtn);
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);
            fadeTransition.setOnFinished(event -> {
                patientPane.getChildren().remove(checkBtn);

                FontIcon newFontIcon = new FontIcon("bi-check-all");
                newFontIcon.setIconColor(Color.GREEN);
                newFontIcon.setIconSize(45);
                newFontIcon.setLayoutX(1515);
                newFontIcon.setLayoutY(361);

                FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), newFontIcon);
                fadeInTransition.setFromValue(0.0);
                fadeInTransition.setToValue(1.0);
                fadeInTransition.play();

                patientPane.getChildren().add(newFontIcon);

                fullNameTxt.setText(patientDetails.getFullname());
                addressTxt.setText(patientDetails.getAddress());
                emailTxt.setText(patientDetails.getEmail());
                familyNameTxt.setText(patientDetails.getFamilyname());
                telTxt.setText(patientDetails.getTelNo());
            });

            fadeTransition.play();
        } else {

        }
    }

    public void setValues(AddNewAppointmentDto addNewAppointmentDto) {
        this.appointmentDto = addNewAppointmentDto;
    }
}