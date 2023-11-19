package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewFrameFormController implements Initializable {

    @FXML
    private JFXButton addBtn;

    @FXML
    private AnchorPane addNewFramePane;

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
    void addBtnOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializetypeCheckBox();
        initializecolorCheckBox();
        initializeMakeForCheckBoxes();
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
