package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;

public class PrescriptionDetailsFormController {

    @FXML
    private Rectangle appointmentIdRec;

    @FXML
    private TextField appointmentIdTxt;

    public void confirmBtnOnAction(ActionEvent actionEvent) {
        System.out.println("hi hi");
    }
}
