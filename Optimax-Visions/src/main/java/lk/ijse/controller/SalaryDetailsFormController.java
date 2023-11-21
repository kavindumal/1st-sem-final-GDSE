package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SalaryDetailsFormController implements Initializable {

    @FXML
    private TextField basicSalaryTxt;

    @FXML
    private TextField dateOfGiving;

    @FXML
    private TextField otHourTxt;

    @FXML
    private PrefixSelectionComboBox<String> positionComboBox;

    @FXML
    private AnchorPane salaryDetailsPane;

    @FXML
    void changeBtnOnAction(ActionEvent event) {
        salaryDetailsPane.getChildren().clear();
        try {
            salaryDetailsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employeeForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> jobTitles = FXCollections.observableArrayList(
                "c.helpers",
                "cashier",
                "IT operator",
                "security",
                "cleaner"
        );

        positionComboBox.setItems(jobTitles);
        positionComboBox.setValue(jobTitles.get(0));
    }
}