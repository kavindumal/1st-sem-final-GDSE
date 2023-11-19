package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.model.FrameModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateFrameFormController implements Initializable {

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXCheckBox colorBlackCb;

    @FXML
    private JFXCheckBox colorColorfulCb;

    @FXML
    private JFXCheckBox colorNeutralCb;

    @FXML
    private JFXCheckBox colorPatternCb;

    @FXML
    private JFXCheckBox colorTortoiseCb;

    @FXML
    private JFXCheckBox colorTranslucentCb;

    @FXML
    private AnchorPane faceShapePane;

    @FXML
    private JFXCheckBox frameAviatorCb;

    @FXML
    private JFXCheckBox frameBrownlineCb;

    @FXML
    private JFXCheckBox frameCatCb;

    @FXML
    private AnchorPane frameColorPane;

    @FXML
    private Rectangle frameIdRec;

    @FXML
    private TextField frameIdTxt;

    @FXML
    private Rectangle frameNameRec;

    @FXML
    private JFXCheckBox frameRoundCb;

    @FXML
    private AnchorPane frameShapePane;

    @FXML
    private JFXCheckBox frameSpecialCb;

    @FXML
    private JFXCheckBox frameSquareCb;

    @FXML
    private Label inputErrorLbl;

    @FXML
    private JFXCheckBox makeForBothCb;

    @FXML
    private JFXCheckBox makeForIndoorCb;

    @FXML
    private JFXCheckBox makeForOutdoorCb;

    @FXML
    private AnchorPane makeForPane;

    @FXML
    private JFXCheckBox materialEcoCb;

    @FXML
    private JFXCheckBox materialMetalCb;

    @FXML
    private JFXCheckBox materialMixedCb;

    @FXML
    private AnchorPane materialPane;

    @FXML
    private JFXCheckBox materialPlasticCb;

    @FXML
    private TextField nameTxt;

    @FXML
    private Rectangle priceRec;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField qtyOnHandTxt;

    @FXML
    private Rectangle qtyRec;

    @FXML
    private JFXCheckBox shapeDiamondCb;

    @FXML
    private JFXCheckBox shapeHeartCb;

    @FXML
    private JFXCheckBox shapeOvalCb;

    @FXML
    private JFXCheckBox shapeRoundCb;

    @FXML
    private JFXCheckBox shapeSquareCb;

    @FXML
    private JFXCheckBox shapeTriangleCb;

    @FXML
    private JFXCheckBox shapeUnsureCb;

    @FXML
    private JFXCheckBox typeBothCb;

    @FXML
    private JFXCheckBox typeFemaleCb;

    @FXML
    private JFXCheckBox typeMaleCb;

    @FXML
    private AnchorPane typePane;

    @FXML
    private AnchorPane updateFramePane;

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FrameModel model = new FrameModel();
        try {
            String[][] values = model.getValues();
            for (int i = 0; i < values.length; i++) {
                if (values[i][0].equals(FrameDetailsFormController.id)) {
                    frameIdTxt.setText(values[i][0]);
                    nameTxt.setText(values[i][1]);
                    checkType(values[i][2]); checkGlass(values[i][3]); checkFace(values[i][4]); checkFrame(values[i][5]); checkFrameColor(values[i][6]); checkMaterial(values[i][7]);
                    qtyOnHandTxt.setText(values[i][8]);
                    priceTxt.setText(values[i][9]);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkMaterial(String s) {
        if (s.equals(materialMetalCb.getText())) materialMetalCb.setSelected(true);
        if (s.equals(materialPlasticCb.getText())) materialPlasticCb.setSelected(true);
        if (s.equals(materialMixedCb.getText())) materialMixedCb.setSelected(true);
        if (s.equals(materialEcoCb.getText())) materialEcoCb.setSelected(true);
    }

    private void checkFrameColor(String s) {
        if (s.equals(colorBlackCb.getText())) colorBlackCb.setSelected(true);
        if (s.equals(colorTortoiseCb.getText())) colorTortoiseCb.setSelected(true);
        if (s.equals(colorPatternCb.getText())) colorPatternCb.setSelected(true);
        if (s.equals(colorNeutralCb.getText())) colorNeutralCb.setSelected(true);
        if (s.equals(colorColorfulCb.getText())) colorColorfulCb.setSelected(true);
        if (s.equals(colorTranslucentCb.getText())) colorTranslucentCb.setSelected(true);
    }

    private void checkFrame(String s) {
        if (s.equals(frameBrownlineCb.getText())) frameBrownlineCb.setSelected(true);
        if (s.equals(frameRoundCb.getText())) frameRoundCb.setSelected(true);
        if (s.equals(frameSquareCb.getText())) frameSquareCb.setSelected(true);
        if (s.equals(frameCatCb.getText())) frameCatCb.setSelected(true);
        if (s.equals(frameAviatorCb.getText())) frameAviatorCb.setSelected(true);
        if (s.equals(frameSpecialCb.getText())) frameSpecialCb.setSelected(true);
    }

    private void checkFace(String s) {
        if (s.equals(shapeTriangleCb.getText())) shapeTriangleCb.setSelected(true);
        if (s.equals(shapeOvalCb.getText())) shapeOvalCb.setSelected(true);
        if (s.equals(shapeSquareCb.getText())) shapeSquareCb.setSelected(true);
        if (s.equals(shapeRoundCb.getText())) shapeRoundCb.setSelected(true);
        if (s.equals(shapeHeartCb.getText())) shapeHeartCb.setSelected(true);
        if (s.equals(shapeDiamondCb.getText())) shapeDiamondCb.setSelected(true);
        if (s.equals(shapeUnsureCb.getText())) shapeUnsureCb.setSelected(true);
    }

    private void checkGlass(String s) {
        if (s.equals(makeForOutdoorCb.getText())) makeForOutdoorCb.setSelected(true);
        if (s.equals(makeForIndoorCb.getText())) makeForIndoorCb.setSelected(true);
        if (s.equals(makeForBothCb.getText())) makeForBothCb.setSelected(true);
    }

    private void checkType(String s) {
        if (s.equals(typeMaleCb.getText())) typeMaleCb.setSelected(true);
        if (s.equals(typeFemaleCb.getText())) typeFemaleCb.setSelected(true);
        if (s.equals(typeBothCb.getText())) typeBothCb.setSelected(true);
    }
}
