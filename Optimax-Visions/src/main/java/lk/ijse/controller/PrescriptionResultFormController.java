package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.dto.FrameDto;
import lk.ijse.model.LenseModel;
import lk.ijse.model.PrescriptionModel;
import lk.ijse.prescriptionGeneratingCase.PrescriptionGenerator;
import lombok.SneakyThrows;
import org.controlsfx.control.PrefixSelectionComboBox;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PrescriptionResultFormController implements Initializable {

    @FXML
    private FontIcon addtoCartFontIcon;

    @FXML
    private PrefixSelectionComboBox<String> leftEyeLenseComboBox;

    @FXML
    private PrefixSelectionComboBox<String> rightEyeLenseComboBox;

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

    public static Image img;
    public static String name;
    public static String lenseChange;

    List<Pane> paneList = new ArrayList<>();
    List<Label> nameList = new ArrayList<Label>();
    List<Label> priceList = new ArrayList<Label>();
    List<ImageView> resultImgList = new ArrayList<ImageView>();
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


    @FXML
    void fontIconClickedAction(MouseEvent event) {
        prescriptionResultPane.getChildren().clear();
        try {
            prescriptionResultPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/presGlassSellForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLenseToComboBox();
        PrescriptionGenerator.checkSuitableLenseForRightEyeGlass();
        PrescriptionGenerator.checkSuitableLenseForLeftEyeGlass();
        addPanesToPaneList();
        addNameLabelsToLabelList();
        addPriceLabelsToLabelList();
        addResultImageToList();
        PrescriptionModel model = new PrescriptionModel();
        List<FrameDto> generatedFrames = model.getGeneratedFrames();
        if (generatedFrames.isEmpty()) {
            ImageView imageView = new ImageView(new Image("img/icons/sorry.gif"));
            imageView.setFitHeight(150);
            imageView.setFitWidth(150);
            imageView.setLayoutX(731);
            imageView.setLayoutY(190);
            Label label1 = new Label();
            Label label2 = new Label();
            label2.setText("Sorry");
            label1.setText("No matching glasses in now.");
            label1.setPrefWidth(869);
            label2.setPrefWidth(869);
            label1.setPrefHeight(77);
            label2.setPrefHeight(77);
            label1.setLayoutX(360);
            label2.setLayoutX(360);
            label1.setLayoutY(430);
            label2.setLayoutY(400);
            label2.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-alignment: center;");
            label1.setStyle("-fx-font-size: 25px; -fx-alignment: center;");
            prescriptionResultPane.getChildren().add(label1);
            prescriptionResultPane.getChildren().add(label2);
            prescriptionResultPane.getChildren().add(imageView);
        } else {
            for (int i = 0; i < generatedFrames.size(); i++) {
                paneList.get(i).setVisible(true);
                paneList.get(i).setOpacity(1);
                resultImgList.get(i).setOpacity(1);
                resultImgList.get(i).setImage(new Image(model.getImage(generatedFrames.get(i).getId())));
                nameList.get(i).setOpacity(1);
                nameList.get(i).setText(generatedFrames.get(i).getName());
                priceList.get(i).setText(String.valueOf(generatedFrames.get(i).getPrice()));

                priceList.get(i).setOpacity(1);
            }
        }
    }

    private void addLenseToComboBox() throws SQLException {
        LenseModel model = new LenseModel();
        String[][] lenseDetails = model.getLenseDetails();
        ObservableList<String> problemChoices = FXCollections.observableArrayList();
        for (int i = 0; i < lenseDetails.length; i++) {
            problemChoices.add(lenseDetails[i][1]);
        }
        leftEyeLenseComboBox.setItems(problemChoices);
        rightEyeLenseComboBox.setItems(problemChoices);
    }

    private void addResultImageToList() {
        resultImgList.add(resultImg1);
        resultImgList.add(resultImg2);
        resultImgList.add(resultImg3);
        resultImgList.add(resultImg4);
        resultImgList.add(resultImg5);
        resultImgList.add(resultImg6);
        resultImgList.add(resultImg7);
        resultImgList.add(resultImg8);
    }

    private void addPriceLabelsToLabelList() {
        priceList.add(priceLbl1);
        priceList.add(priceLbl11);
        priceList.add(priceLbl111);
        priceList.add(priceLbl1111);
        priceList.add(priceLbl11111111);
        priceList.add(priceLbl1111111);
        priceList.add(priceLbl111111);
        priceList.add(priceLbl11111);
    }

    private void addNameLabelsToLabelList() {
        nameList.add(resultLbl1);
        nameList.add(resultLbl11);
        nameList.add(resultLbl111);
        nameList.add(resultLbl1111);
        nameList.add(resultLbl11111111);
        nameList.add(resultLbl1111111);
        nameList.add(resultLbl111111);
        nameList.add(resultLbl11111);
    }

    private void addPanesToPaneList() {
        paneList.add(results1Pane);
        paneList.add(results2Pane);
        paneList.add(results3Pane);
        paneList.add(results4Pane);
        paneList.add(results5Pane);
        paneList.add(results6Pane);
        paneList.add(results7Pane);
        paneList.add(results8Pane);
    }
}