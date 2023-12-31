package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectMeasurementFormController implements Initializable {

    @FXML
    private JFXComboBox<String> bridgeWidthCombo;

    @FXML
    private JFXComboBox<String> lenseWidthCombo;

    @FXML
    private JFXComboBox<String> templeWidthCombo;

    @FXML
    private AnchorPane measurePane;

    public static int widthOfLense;
    public static int widthOfBridge;
    public static int widthOfTemple;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> lenseWidth = FXCollections.observableArrayList();
        int lenseWidths = 30;
        do {
            lenseWidth.add("" + lenseWidths);
            lenseWidths = lenseWidths + 1;

        } while (lenseWidths <= 66);
        lenseWidthCombo.setItems(lenseWidth);

        ObservableList<String> bridgeWidth = FXCollections.observableArrayList();
        int bridgeWidths = 10;
        do {
            bridgeWidth.add("" + bridgeWidths);
            bridgeWidths = bridgeWidths + 1;

        } while (bridgeWidths <= 30);
        bridgeWidthCombo.setItems(bridgeWidth);

        ObservableList<String> templeLength = FXCollections.observableArrayList();
        int templeLengths = 120;
        do {
            templeLength.add("" + templeLengths);
            templeLengths = templeLengths + 1;

        } while (templeLengths <= 150);
        templeWidthCombo.setItems(templeLength);
    }

    public void nextBtnOnAction(ActionEvent actionEvent) {
        if (!lenseWidthCombo.getValue().isEmpty()) {
            if (!bridgeWidthCombo.getValue().isEmpty()) {
                if (!templeWidthCombo.getValue().isEmpty()) {
                    widthOfBridge = Integer.parseInt(bridgeWidthCombo.getValue());
                    widthOfLense = Integer.parseInt(lenseWidthCombo.getValue());
                    widthOfTemple = Integer.parseInt(templeWidthCombo.getValue());
                    measurePane.getChildren().clear();
                    try {
                        measurePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/specialFeaturesForm.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}