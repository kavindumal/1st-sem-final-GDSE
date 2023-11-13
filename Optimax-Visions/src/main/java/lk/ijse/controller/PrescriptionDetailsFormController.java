package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionDetailsFormController implements Initializable {

    @FXML
    private JFXComboBox<?> addLeftCiomboBox;

    @FXML
    private JFXComboBox<?> addRightCiomboBox;

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
    private JFXComboBox<?> cylinderLeftComboBox;

    @FXML
    private JFXComboBox<?> cylinderRightComboBox;

    @FXML
    private JFXComboBox<String> sphereLeftComboBox;

    @FXML
    private JFXComboBox<String> sphereRightComboBox;

    @FXML
    private JFXCheckBox twoPdCheckBox;

    @FXML
    void confirmBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> sphereRightList = FXCollections.observableArrayList();
        double countsphere = -16.00;
        do {
            sphereRightList.add("\t" + countsphere);
            countsphere = countsphere + 0.25;

        } while (countsphere <= 12.00);
        sphereRightComboBox.setItems(sphereRightList);
        sphereLeftComboBox.setItems(sphereRightList);

        ObservableList<String> CylinderRightList = FXCollections.observableArrayList();
        double countRight = -6.00;
        do {
            sphereRightList.add("\t" + countRight);
            countRight = countRight + 0.25;

        } while (countRight <= 6.00);
        sphereRightComboBox.setItems(sphereRightList);

        ObservableList<String> addRightList = FXCollections.observableArrayList();
        double countAdd = 0.00;
        do {
            sphereRightList.add("\t" + countAdd);
            countAdd = countAdd + 0.25;

        } while (countAdd <= 08.00);
        sphereRightComboBox.setItems(sphereRightList);
    }
}