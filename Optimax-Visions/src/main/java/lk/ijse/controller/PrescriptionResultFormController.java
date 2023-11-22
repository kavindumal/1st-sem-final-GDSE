package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.model.LenseModel;
import lombok.SneakyThrows;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionResultFormController implements Initializable {

    @FXML
    private FontIcon addtoCartFontIcon;

    @FXML
    private PrefixSelectionComboBox<String> lenseChangeComboBox;

    @FXML
    private AnchorPane prescriptionResultPane;

    @FXML
    private Label priceLbl1;

    @FXML
    private Label priceLbl11;

    @FXML
    private Label priceLbl111;

    @FXML
    private Label priceLbl1111;

    @FXML
    private Label priceLbl11111;

    @FXML
    private Label priceLbl111111;

    @FXML
    private Label priceLbl1111111;

    @FXML
    private Label priceLbl11111111;

    @FXML
    private ImageView resultImg1;

    @FXML
    private ImageView resultImg2;

    @FXML
    private ImageView resultImg3;

    @FXML
    private ImageView resultImg4;

    @FXML
    private ImageView resultImg5;

    @FXML
    private ImageView resultImg6;

    @FXML
    private ImageView resultImg7;

    @FXML
    private ImageView resultImg8;

    @FXML
    private Label resultLbl1;

    @FXML
    private Label resultLbl11;

    @FXML
    private Label resultLbl111;

    @FXML
    private Label resultLbl1111;

    @FXML
    private Label resultLbl11111;

    @FXML
    private Label resultLbl111111;

    @FXML
    private Label resultLbl1111111;

    @FXML
    private Label resultLbl11111111;

    @FXML
    private Pane results1Pane;

    @FXML
    private Pane results2Pane;

    @FXML
    private Pane results3Pane;

    @FXML
    private Pane results4Pane;

    @FXML
    private Pane results5Pane;

    @FXML
    private Pane results6Pane;

    @FXML
    private Pane results7Pane;

    @FXML
    private Pane results8Pane;

    @FXML
    void results1PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results1PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results2PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results2PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results3PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results3PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results4PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results4PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results5PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results5PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results6PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results6PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results7PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results7PaneOnMouseExited(MouseEvent event) {

    }

    @FXML
    void results8PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results8PaneOnMouseExited(MouseEvent event) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> lenseOb = FXCollections.observableArrayList();
        LenseModel model = new LenseModel();
        String[][] lenseDetails = model.getLenseDetails();
        for (int i = 0; i < lenseDetails.length; i++) {
            lenseOb.add(lenseDetails[i][1]);
        }
        lenseChangeComboBox.setItems(lenseOb);
        if (PrescriptionDetailsFormController.prescriptionSphere > 0) {
            lenseChangeComboBox.setValue(lenseDetails[2][1]);
        } else {
            lenseChangeComboBox.setValue(lenseDetails[1][1]);
        }
    }
}