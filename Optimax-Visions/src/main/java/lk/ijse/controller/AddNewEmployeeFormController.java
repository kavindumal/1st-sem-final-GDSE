package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewEmployeeFormController implements Initializable {

    @FXML
    private JFXButton addBtn;

    @FXML
    private AnchorPane addNewEmployeePane;

    @FXML
    private DatePicker dateOfBirthDP;

    @FXML
    private PrefixSelectionComboBox<String> jobTitleComboBox;

    @FXML
    private Rectangle lenseNameRec;

    @FXML
    private Rectangle lenseNameRec1;

    @FXML
    private Rectangle lenseNameRec111;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField nicNumberTxt;

    @FXML
    private TextField telNoTxt;

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> jobTitles = FXCollections.observableArrayList(
                "c.helpers",
                "cashier",
                "IT team",
                "security",
                "cleaner"
        );

        jobTitleComboBox.setItems(jobTitles);
        jobTitleComboBox.setValue(jobTitles.get(0));
    }
}
