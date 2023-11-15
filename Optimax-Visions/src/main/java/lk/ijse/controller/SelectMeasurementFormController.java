package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectMeasurementFormController implements Initializable {

    @FXML
    private JFXComboBox<String> bridgeWidthCombo;

    @FXML
    private JFXComboBox<String> lenseWidthCombo;

    @FXML
    private JFXComboBox<String> templeWidthCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> lenseWidth = FXCollections.observableArrayList();
        int lenseWidths = 30;
        do {
            lenseWidth.add("\t" + lenseWidths);
            lenseWidths = lenseWidths + 1;

        } while (lenseWidths <= 66);
        lenseWidthCombo.setItems(lenseWidth);

        ObservableList<String> bridgeWidth = FXCollections.observableArrayList();
        int bridgeWidths = 10;
        do {
            bridgeWidth.add("\t" + bridgeWidths);
            bridgeWidths = bridgeWidths + 1;

        } while (bridgeWidths <= 30);
        bridgeWidthCombo.setItems(bridgeWidth);

        ObservableList<String> templeLength = FXCollections.observableArrayList();
        int templeLengths = 120;
        do {
            templeLength.add("\t" + templeLengths);
            templeLengths = templeLengths + 1;

        } while (templeLengths <= 150);
        templeWidthCombo.setItems(templeLength);
    }
}