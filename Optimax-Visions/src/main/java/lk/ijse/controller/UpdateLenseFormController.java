package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.dto.LenseDetailsDto;
import lk.ijse.model.LenseModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateLenseFormController implements Initializable {

    @FXML
    private AnchorPane updatePane;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXCheckBox antyReflectiveCb;

    @FXML
    private JFXCheckBox blueLightCb;

    @FXML
    private JFXCheckBox bothFNCb;

    @FXML
    private JFXCheckBox farCb;

    @FXML
    private Rectangle lenseIdRec;

    @FXML
    private TextField lenseIdtTxt;

    @FXML
    private Rectangle lenseNameRec;

    @FXML
    private TextField nameTxt;

    @FXML
    private JFXCheckBox nearCb;

    @FXML
    private Rectangle priceRec;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField qtyOnHandTxt;

    @FXML
    private Rectangle qtyRec;

    LenseModel lenseModel = new LenseModel();

    @FXML
    void addBtnOnAction(ActionEvent event) throws SQLException {
        if (lenseModel.updateDatabase(new LenseDetailsDto(lenseIdtTxt.getText(), nameTxt.getText(), getTypeForCheckBox(), Integer.parseInt(qtyOnHandTxt.getText()), Double.parseDouble(priceTxt.getText())))) {
            updatePane.getChildren().clear();
            try {
                updatePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/lensesDetailsForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getTypeForCheckBox() {
        return farCb.isSelected() ? farCb.getText() : nearCb.isSelected() ? nearCb.getText() : bothFNCb.isSelected() ? bothFNCb.getText() : blueLightCb.isSelected() ? blueLightCb.getText() : antyReflectiveCb.isSelected() ? antyReflectiveCb.getText() : null;
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lenseIdtTxt.setText(LensesDetailsFormController.id);
        String[][] lenseDetails = lenseModel.getLenseDetails();
        for (int i = 0; i < lenseDetails.length; i++) {
            if (lenseDetails[i][0].equals(LensesDetailsFormController.id)) {
                nameTxt.setText(lenseDetails[i][1]);
                qtyOnHandTxt.setText(lenseDetails[i][3]);
                priceTxt.setText(lenseDetails[i][4]);
                setCb(lenseDetails[i][2]);
            }
        }
        initializeLenseForCheckBox();
    }

    private void setCb(String s) {
        if (s.equals(farCb.getText())) {
            farCb.setSelected(true);
        } else if (s.equals(nearCb.getText())) {
            nearCb.setSelected(true);
        } else if (s.equals(bothFNCb.getText())) {
            bothFNCb.setSelected(true);
        } else if (s.equals(blueLightCb.getText())) {
            blueLightCb.setSelected(true);
        } else {
            antyReflectiveCb.setSelected(true);
        }
    }

    private void initializeLenseForCheckBox() {
        farCb.setOnAction(event -> handleLenseFOrCheckBoxAction(nearCb, bothFNCb, blueLightCb, antyReflectiveCb));
        nearCb.setOnAction(event -> handleLenseFOrCheckBoxAction(farCb, bothFNCb, blueLightCb, antyReflectiveCb));
        bothFNCb.setOnAction(event -> handleLenseFOrCheckBoxAction(nearCb, farCb, blueLightCb, antyReflectiveCb));
        blueLightCb.setOnAction(event -> handleLenseFOrCheckBoxAction(nearCb, bothFNCb, farCb, antyReflectiveCb));
        antyReflectiveCb.setOnAction(event -> handleLenseFOrCheckBoxAction(nearCb, bothFNCb, blueLightCb, farCb));
    }

    private void handleLenseFOrCheckBoxAction(JFXCheckBox material1, JFXCheckBox material2, JFXCheckBox material3, JFXCheckBox material4) {
        material1.setSelected(false);
        material2.setSelected(false);
        material3.setSelected(false);
        material4.setSelected(false);
    }
}