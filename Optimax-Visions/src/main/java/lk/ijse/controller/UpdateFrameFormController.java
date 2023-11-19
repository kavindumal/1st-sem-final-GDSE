package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.FrameDto;
import lk.ijse.model.FrameModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
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
    void addBtnOnAction(ActionEvent event) throws SQLException {
        FrameModel model = new FrameModel();
        AlertSound alertSound = new AlertSound();
        if (!nameTxt.getText().isEmpty()) {
            frameNameRec.setStroke(Color.BLACK); inputErrorLbl.setText("");
            if (checkTypeOnClicked()) {
                inputErrorLbl.setText("");
                if (checkMakeForOnClicked()) {
                    inputErrorLbl.setText("");
                    if (checkFaceShapeOnClicked()) {
                        inputErrorLbl.setText("");
                        if (checkFrameShapeOnClicked()) {
                            inputErrorLbl.setText("");
                            if (checkFrameCOlorOnClicked()) {
                                inputErrorLbl.setText("");
                                if (checkMaterialOnClicked()) {
                                    inputErrorLbl.setText("");
                                    if (!qtyOnHandTxt.getText().isEmpty()) {
                                        inputErrorLbl.setText(""); qtyRec.setStroke(Color.BLACK);
                                        if (!priceTxt.getText().isEmpty()) {
                                            if (model.updateDetails(new FrameDto(frameIdTxt.getText(), nameTxt.getText(), getTypeCheckBox(), getMakeForCheckBox(), getFaceShapeCheckBox(), getFrameShapeCheckBox(), getFrameColorCheckBOx(), getMaterialCheckBox(), Integer.parseInt(qtyOnHandTxt.getText()), Double.parseDouble(priceTxt.getText())))) {
                                                updateFramePane.getChildren().clear();
                                                try {
                                                    updateFramePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/frameDetailsForm.fxml"))));
                                                } catch (IOException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        } else inputErrorLbl.setText("Please enter Frame price !"); alertSound.checkSounds(Sounds.INVALID); priceRec.setStroke(Color.RED);
                                    } else inputErrorLbl.setText("Please enter quantity on hand !"); alertSound.checkSounds(Sounds.INVALID); qtyRec.setStroke(Color.RED);
                                } else inputErrorLbl.setText("Please select Frame Material !"); alertSound.checkSounds(Sounds.INVALID);
                            } else inputErrorLbl.setText("Please select Frame Color !"); alertSound.checkSounds(Sounds.INVALID);
                        } else inputErrorLbl.setText("Please select Frame Shape !"); alertSound.checkSounds(Sounds.INVALID);
                    } else inputErrorLbl.setText("Please select Face Shape of His/Her!"); alertSound.checkSounds(Sounds.INVALID);
                } else inputErrorLbl.setText("Please select Frame Make for !"); alertSound.checkSounds(Sounds.INVALID);
            } else inputErrorLbl.setText("Please select frame Type!"); alertSound.checkSounds(Sounds.INVALID);
        } else frameNameRec.setStroke(Color.RED); inputErrorLbl.setText("Please enter name of the frame !"); alertSound.checkSounds(Sounds.INVALID);
    }

    private String getMaterialCheckBox() {
        return materialMetalCb.isSelected() ? materialMetalCb.getText() : materialPlasticCb.isSelected() ? materialPlasticCb.getText() : materialMixedCb.isSelected() ? materialMixedCb.getText() : materialEcoCb.getText();
    }

    private String getFrameColorCheckBOx() {
        return colorBlackCb.isSelected() ? colorBlackCb.getText() : colorTortoiseCb.isSelected() ? colorTortoiseCb.getText() : colorPatternCb.isSelected() ? colorPatternCb.getText() : colorNeutralCb.isSelected() ? colorNeutralCb.getText() : colorColorfulCb.isSelected() ? colorColorfulCb.getText() : colorTranslucentCb.getText();
    }

    private String getFrameShapeCheckBox() {
        return frameBrownlineCb.isSelected() ? frameBrownlineCb.getText() : frameRoundCb.isSelected() ? frameRoundCb.getText() : frameSquareCb.isSelected() ? frameSquareCb.getText() : frameCatCb.isSelected() ? frameCatCb.getText() : frameAviatorCb.isSelected() ? frameAviatorCb.getText() : frameSpecialCb.getText();
    }

    private String getFaceShapeCheckBox() {
        return shapeTriangleCb.isSelected() ? shapeTriangleCb.getText() : shapeOvalCb.isSelected() ? shapeOvalCb.getText() : shapeSquareCb.isSelected() ? shapeSquareCb.getText() : shapeRoundCb.isSelected() ? shapeRoundCb.getText() : shapeHeartCb.isSelected() ? shapeHeartCb.getText() : shapeDiamondCb.isSelected() ? shapeDiamondCb.getText() : shapeUnsureCb.getText();
    }

    private String getMakeForCheckBox() {
        return makeForOutdoorCb.isSelected() ? makeForOutdoorCb.getText() : makeForIndoorCb.isSelected() ? makeForIndoorCb.getText() : makeForBothCb.getText();
    }

    private String getTypeCheckBox() {
        if (typeMaleCb.isSelected()) return typeMaleCb.getText();
        else if (typeFemaleCb.isSelected()) return typeFemaleCb.getText();
        return typeBothCb.getText();
    }

    private boolean checkMaterialOnClicked() {
        return materialMetalCb.isSelected() || materialPlasticCb.isSelected() || materialMixedCb.isSelected() || materialEcoCb.isSelected();
    }

    private boolean checkFrameCOlorOnClicked() {
        return colorBlackCb.isSelected() || colorTortoiseCb.isSelected() || colorPatternCb.isSelected() || colorNeutralCb.isSelected() || colorColorfulCb.isSelected() || colorTranslucentCb.isSelected();
    }

    private boolean checkFrameShapeOnClicked() {
        return frameBrownlineCb.isSelected() || frameRoundCb.isSelected() || frameSquareCb.isSelected() || frameCatCb.isSelected() || frameAviatorCb.isSelected() || frameSpecialCb.isSelected();
    }

    private boolean checkFaceShapeOnClicked() {
        return shapeTriangleCb.isSelected() || shapeOvalCb.isSelected() || shapeSquareCb.isSelected() || shapeRoundCb.isSelected() || shapeHeartCb.isSelected() || shapeDiamondCb.isSelected() || shapeUnsureCb.isSelected();
    }

    private boolean checkMakeForOnClicked() {
        return makeForOutdoorCb.isSelected() || makeForIndoorCb.isSelected() || makeForBothCb.isSelected();
    }

    private boolean checkTypeOnClicked() {
        return typeMaleCb.isSelected() || typeFemaleCb.isSelected() || typeBothCb.isSelected();
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
        initializetypeCheckBox();
        initializecolorCheckBox();
        initializeMakeForCheckBoxes();
        initializeShapeCheckBoxes();
        initializeFrameCheckBoxes();
        initializeMaterialCheckBoxes();
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

    private void initializeMaterialCheckBoxes() {
        materialEcoCb.setOnAction(event -> handleMaterialCheckBoxAction(materialMetalCb, materialMixedCb, materialPlasticCb));
        materialMetalCb.setOnAction(event -> handleMaterialCheckBoxAction(materialEcoCb, materialMixedCb, materialPlasticCb));
        materialMixedCb.setOnAction(event -> handleMaterialCheckBoxAction(materialEcoCb, materialMetalCb, materialPlasticCb));
        materialPlasticCb.setOnAction(event -> handleMaterialCheckBoxAction(materialEcoCb, materialMetalCb, materialMixedCb));
    }

    private void handleMaterialCheckBoxAction(JFXCheckBox material1, JFXCheckBox material2, JFXCheckBox material3) {
        material1.setSelected(false);
        material2.setSelected(false);
        material3.setSelected(false);
    }

    private void initializeFrameCheckBoxes() {
        frameAviatorCb.setOnAction(event -> handleFrameCheckBoxAction(frameBrownlineCb, frameCatCb, frameRoundCb, frameSquareCb, frameSpecialCb));
        frameBrownlineCb.setOnAction(event -> handleFrameCheckBoxAction(frameAviatorCb, frameCatCb, frameRoundCb, frameSquareCb, frameSpecialCb));
        frameCatCb.setOnAction(event -> handleFrameCheckBoxAction(frameAviatorCb, frameBrownlineCb, frameRoundCb, frameSquareCb, frameSpecialCb));
        frameRoundCb.setOnAction(event -> handleFrameCheckBoxAction(frameAviatorCb, frameBrownlineCb, frameCatCb, frameSquareCb, frameSpecialCb));
        frameSquareCb.setOnAction(event -> handleFrameCheckBoxAction(frameAviatorCb, frameBrownlineCb, frameCatCb, frameRoundCb, frameSpecialCb));
        frameSpecialCb.setOnAction(event -> handleFrameCheckBoxAction(frameAviatorCb, frameBrownlineCb, frameCatCb, frameRoundCb, frameSquareCb));
    }

    private void handleFrameCheckBoxAction(JFXCheckBox... checkboxes) {
        for (JFXCheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    private void initializeShapeCheckBoxes() {
        shapeDiamondCb.setOnAction(event -> handleShapeCheckBoxAction(shapeHeartCb, shapeOvalCb, shapeRoundCb, shapeSquareCb, shapeTriangleCb, shapeUnsureCb));
        shapeHeartCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeOvalCb, shapeRoundCb, shapeSquareCb, shapeTriangleCb, shapeUnsureCb));
        shapeOvalCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeHeartCb, shapeRoundCb, shapeSquareCb, shapeTriangleCb, shapeUnsureCb));
        shapeRoundCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeHeartCb, shapeOvalCb, shapeSquareCb, shapeTriangleCb, shapeUnsureCb));
        shapeSquareCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeHeartCb, shapeOvalCb, shapeRoundCb, shapeTriangleCb, shapeUnsureCb));
        shapeTriangleCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeHeartCb, shapeOvalCb, shapeRoundCb, shapeSquareCb, shapeUnsureCb));
        shapeUnsureCb.setOnAction(event -> handleShapeCheckBoxAction(shapeDiamondCb, shapeHeartCb, shapeOvalCb, shapeRoundCb, shapeSquareCb, shapeTriangleCb));
    }

    private void handleShapeCheckBoxAction(JFXCheckBox... checkboxes) {
        for (JFXCheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    private void initializeMakeForCheckBoxes() {
        makeForBothCb.setOnAction(event -> handleMakeForCheckBoxAction(makeForIndoorCb, makeForOutdoorCb));
        makeForIndoorCb.setOnAction(event -> handleMakeForCheckBoxAction(makeForBothCb, makeForOutdoorCb));
        makeForOutdoorCb.setOnAction(event -> handleMakeForCheckBoxAction(makeForBothCb, makeForIndoorCb));
    }

    private void handleMakeForCheckBoxAction(JFXCheckBox makeFor1, JFXCheckBox makeFor2) {
        makeFor1.setSelected(false);
        makeFor2.setSelected(false);
    }

    private void initializecolorCheckBox() {
        colorBlackCb.setOnAction(event -> handleColorCheckBoxAction(colorColorfulCb, colorNeutralCb, colorPatternCb, colorTortoiseCb, colorTranslucentCb));
        colorColorfulCb.setOnAction(event -> handleColorCheckBoxAction(colorBlackCb, colorNeutralCb, colorPatternCb, colorTortoiseCb, colorTranslucentCb));
        colorNeutralCb.setOnAction(event -> handleColorCheckBoxAction(colorBlackCb, colorColorfulCb, colorPatternCb, colorTortoiseCb, colorTranslucentCb));
        colorPatternCb.setOnAction(event -> handleColorCheckBoxAction(colorBlackCb, colorColorfulCb, colorNeutralCb, colorTortoiseCb, colorTranslucentCb));
        colorTortoiseCb.setOnAction(event -> handleColorCheckBoxAction(colorBlackCb, colorColorfulCb, colorNeutralCb, colorPatternCb, colorTranslucentCb));
        colorTranslucentCb.setOnAction(event -> handleColorCheckBoxAction(colorBlackCb, colorColorfulCb, colorNeutralCb, colorPatternCb, colorTortoiseCb));

    }

    private void initializetypeCheckBox() {
        typeMaleCb.setOnAction(event -> handleTypeCheckBoxAction(typeFemaleCb,typeBothCb));
        typeFemaleCb.setOnAction(event -> handleTypeCheckBoxAction(typeMaleCb,typeBothCb));
        typeBothCb.setOnAction(event -> handleTypeCheckBoxAction(typeFemaleCb,typeMaleCb));
    }

    private void handleColorCheckBoxAction(JFXCheckBox... checkboxes) {
        for (JFXCheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    private void handleTypeCheckBoxAction(JFXCheckBox type1, JFXCheckBox type2) {
        type1.setSelected(false);
        type2.setSelected(false);
    }
}
