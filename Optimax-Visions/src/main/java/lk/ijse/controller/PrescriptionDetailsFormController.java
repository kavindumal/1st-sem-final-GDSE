package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.model.PrescriptionModel;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class PrescriptionDetailsFormController implements Initializable {

    @FXML
    private JFXComboBox<String> addLeftCiomboBox;

    @FXML
    private JFXComboBox<String> addRightCiomboBox;

    @FXML
    private JFXComboBox<String> pdComboBox;

    @FXML
    private Rectangle appointmentIdRec;

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private TextField axisLeftComboBox;

    @FXML
    private TextField axisRightComboBox;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXComboBox<String> cylinderLeftComboBox;

    @FXML
    private JFXComboBox<String> cylinderRightComboBox;

    @FXML
    private JFXComboBox<String> sphereLeftComboBox;

    @FXML
    private JFXComboBox<String> sphereRightComboBox;

    @FXML
    private JFXCheckBox twoPdCheckBox;

    @FXML
    private AnchorPane prescriptionDetailsPane;

    public static double sphereRight;
    public static double sphereLeft;
    public static double cylRight;
    public static double cylLeft;
    public static int axisRight;
    public static int axisLeft;
    public static double addRight;
    public static double addLeft;
    public static int pd;
    public static String appointmentId;

    @FXML
    void confirmBtnOnAction(ActionEvent event) {
        if (count == 1) {
            if (!sphereRightComboBox.getValue().isEmpty()) {
                if (!sphereLeftComboBox.getValue().isEmpty()) {
                    if (!cylinderRightComboBox.getValue().isEmpty()) {
                        if (!cylinderLeftComboBox.getValue().isEmpty()) {
                            if (!axisRightComboBox.getText().isEmpty()) {
                                if (!axisLeftComboBox.getText().isEmpty()) {
                                    sphereRight = Double.parseDouble(sphereRightComboBox.getValue());
                                    sphereLeft = Double.parseDouble(sphereLeftComboBox.getValue());
                                    cylRight = Double.parseDouble(cylinderRightComboBox.getValue());
                                    cylLeft = Double.parseDouble(cylinderLeftComboBox.getValue());
                                    axisRight = Integer.parseInt(axisRightComboBox.getText());
                                    axisLeft = Integer.parseInt(axisLeftComboBox.getText());
                                    addRight = Double.parseDouble(addRightCiomboBox.getValue());
                                    addLeft = Double.parseDouble(addLeftCiomboBox.getValue());
                                    pd = Integer.parseInt(pdComboBox.getValue());

                                    prescriptionDetailsPane.getChildren().clear();
                                    try {
                                        prescriptionDetailsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectGenderForm.fxml"))));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    boolean checkAppointmentId = false;

    @FXML
    private Label invalidAppointmentIdTxt;

    private int count;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        count = 0;
        PrescriptionModel model = new PrescriptionModel();
        JFXButton button = new JFXButton("Check");
        button.setLayoutX(1389);
        button.setLayoutY(170);
        button.setPrefWidth(180);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-radius: 30; -fx-background-color: blue; -fx-text-fill: white; -fx-font-size: 20px");

        button.setOnAction(event -> {
            try {
                if (model.checkAppointmentId(appointmentIdTxt.getText())){
                    invalidAppointmentIdTxt.setOpacity(0.0);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), button);
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(0.0);
                    fadeTransition.setOnFinished(e -> {
                        prescriptionDetailsPane.getChildren().remove(button);

                        FontIcon newFontIcon = new FontIcon("bi-check-all");
                        newFontIcon.setIconColor(Color.GREEN);
                        newFontIcon.setIconSize(45);
                        newFontIcon.setLayoutX(1445);
                        newFontIcon.setLayoutY(216);

                        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1000), newFontIcon);
                        fadeInTransition.setFromValue(0.0);
                        fadeInTransition.setToValue(1.0);
                        fadeInTransition.play();

                        count = 1;
                        prescriptionDetailsPane.getChildren().add(newFontIcon);
                        appointmentIdTxt.setEditable(false);
                    });
                    fadeTransition.play();
                    checkAppointmentId = true;
                    appointmentId = appointmentIdTxt.getText();
                }
                else {
                    AlertSound alertSound = new AlertSound();
                    alertSound.checkSounds(Sounds.INVALID);
                    invalidAppointmentIdTxt.setOpacity(1.0);
                    checkAppointmentId = false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        prescriptionDetailsPane.getChildren().add(button);
        ObservableList<String> pdList = FXCollections.observableArrayList();
        int pd = 46;
        do {
            pdList.add("" + pd);
            pd = pd + 1;

        } while (pd <= 80);
        pdComboBox.setItems(pdList);

        ObservableList<String> sphereRightList = FXCollections.observableArrayList();
        double countsphere = -16.00;
        do {
            sphereRightList.add("" + countsphere);
            countsphere = countsphere + 0.25;

        } while (countsphere <= 12.00);
        sphereRightComboBox.setItems(sphereRightList);
        sphereLeftComboBox.setItems(sphereRightList);

        ObservableList<String> CylinderRightList = FXCollections.observableArrayList();
        double countRight = -6.00;
        do {
            CylinderRightList.add("" + countRight);
            countRight = countRight + 0.25;

        } while (countRight <= 6.00);
        cylinderLeftComboBox.setItems(CylinderRightList);
        cylinderRightComboBox.setItems(CylinderRightList);

        ObservableList<String> addRightList = FXCollections.observableArrayList();
        double countAdd = 0.00;
        do {
            addRightList.add("" + countAdd);
            countAdd = countAdd + 0.25;

        } while (countAdd <= 08.00);
        addRightCiomboBox.setItems(addRightList);
        addLeftCiomboBox.setItems(addRightList);
    }
}