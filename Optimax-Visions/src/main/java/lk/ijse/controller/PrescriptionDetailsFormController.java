package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
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

    public static Double prescriptionSphere;

    @FXML
    void confirmBtnOnAction(ActionEvent event) {

        prescriptionSphere = Double.valueOf(sphereLeftComboBox.getValue());
        prescriptionDetailsPane.getChildren().clear();
        try {
            prescriptionDetailsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/selectGenderForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> pdList = FXCollections.observableArrayList();
        int pd = 46;
        do {
            pdList.add("\t" + pd);
            pd = pd + 1;

        } while (pd <= 80);
        pdComboBox.setItems(pdList);

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
            CylinderRightList.add("\t" + countRight);
            countRight = countRight + 0.25;

        } while (countRight <= 6.00);
        cylinderLeftComboBox.setItems(CylinderRightList);
        cylinderRightComboBox.setItems(CylinderRightList);

        ObservableList<String> addRightList = FXCollections.observableArrayList();
        double countAdd = 0.00;
        do {
            addRightList.add("\t" + countAdd);
            countAdd = countAdd + 0.25;

        } while (countAdd <= 08.00);
        addRightCiomboBox.setItems(addRightList);
        addLeftCiomboBox.setItems(addRightList);
    }
}