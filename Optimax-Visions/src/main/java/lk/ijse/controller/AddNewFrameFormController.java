package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.FrameDto;
import lk.ijse.model.ForgotModel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Objects;
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
    private Label inputErrorLbl;

    @FXML
    private ImageView framePhoto;

    String framePhotoLink = "";

    ForgotModel model = new ForgotModel();

    @FXML
    void addBtnOnAction(ActionEvent event) throws SQLException {
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
                                            if (model.setDetailsToDatabase(new FrameDto(frameIdTxt.getText(), nameTxt.getText(), getTypeCheckBox(), getMakeForCheckBox(), getFaceShapeCheckBox(), getFrameShapeCheckBox(), getFrameColorCheckBOx(), getMaterialCheckBox(), Integer.parseInt(qtyOnHandTxt.getText()), Double.parseDouble(priceTxt.getText())), getPhotoLink())) {
                                                addNewFramePane.getChildren().clear();
                                                try {
                                                    addNewFramePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/frameDetailsForm.fxml"))));
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

    String destinationFolderPath = "C:\\Users\\Kavindu\\Documents\\GDSE 68\\1 st sem Final Project\\eye clinic\\software\\1st-sem-final-GDSE\\Optimax-Visions\\src\\main\\resources\\img\\framePhotos";

    private String getPhotoLink() {
        String link = "";
        if (!framePhotoLink.isEmpty()) {
            try {
                File sourceFile = new File(new URL(framePhotoLink).toURI());
                Path destinationFolderPath = Paths.get(this.destinationFolderPath);
                if (!Files.exists(destinationFolderPath)) {
                    Files.createDirectories(destinationFolderPath);
                }

                String fileName = nameTxt.getText() + ".png";
                Path destinationFilePath = destinationFolderPath.resolve(fileName);
                link = "img/framePhotos/" + fileName;
                Files.copy(sourceFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else link = "img/framePhotos/noPhoto.png";
        return link;
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

    String frameId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            frameId = model.getFrameId();
            frameIdTxt.setText(frameId);
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

    @FXML
    public void backOnAction(MouseEvent event) {

    }

    @FXML
    public void framePhotoOnMouseClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a photo");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = new Stage();
        centerStage(stage);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            framePhotoLink = selectedFile.toURI().toString();
            framePhoto.setImage(image);
        }
    }

    private void centerStage(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2.0;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2.0;

        stage.setX(centerX);
        stage.setY(centerY);
    }
}
