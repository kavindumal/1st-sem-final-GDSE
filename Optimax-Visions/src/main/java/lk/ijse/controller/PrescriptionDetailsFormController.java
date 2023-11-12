package lk.ijse.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionDetailsFormController implements Initializable {

    public Rectangle appointmentIdRec;
    public TextField appointmentIdTxt;
    public TableView<String> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
