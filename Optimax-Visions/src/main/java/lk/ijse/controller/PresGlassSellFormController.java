package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lk.ijse.model.FrameModel;
import lk.ijse.model.LenseModel;
import lombok.SneakyThrows;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class PresGlassSellFormController implements Initializable {

    @FXML
    private FontIcon addtoCartFontIcon;

    @FXML
    private Rectangle customerGIvenPriceRec;

    @FXML
    private TextField customerGivenPriceTxt;

    @FXML
    private Label discountPriceLbl;

    @FXML
    private Label framePriceLbl;

    @FXML
    private Label lensePriceLbl;

    @FXML
    private Label prescriptionPriceLbl;

    @FXML
    private ImageView resultImg1;

    @FXML
    private Label resultLbl1;

    @FXML
    private Pane results1Pane;

    @FXML
    private Label specialFeaturesPriceLbl;

    @FXML
    private Label subTotalPriceLbl;

    @FXML
    private Label totalPriceLbl;

    @FXML
    void results1PaneOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void results1PaneOnMouseExited(MouseEvent event) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FrameModel model = new FrameModel();
        LenseModel lenseModel = new LenseModel();
        String[][] values = model.getValues();
        for (int i = 0; i < values.length; i++) {
            if (values[i][1].equals(PrescriptionResultFormController.name)) {
                resultLbl1.setText(values[i][0]);
                resultImg1.setImage(new Image("img/prescriptionGlass/coexist.png"));
                framePriceLbl.setText(values[i][9]);
            }
        }
        String[][] lenseDetails = lenseModel.getLenseDetails();
        for (int i = 0; i < lenseDetails.length; i++) {
            if (PrescriptionResultFormController.lenseChange.equals(lenseDetails[i][1])) {
                lensePriceLbl.setText(lenseDetails[i][4]);
            }
        }
        prescriptionPriceLbl.setText("500");
        specialFeaturesPriceLbl.setText("-");
        Double subTotal = Double.parseDouble(framePriceLbl.getText()) + Double.parseDouble(lensePriceLbl.getText()) + Double.parseDouble(prescriptionPriceLbl.getText());
        discountPriceLbl.setText("-");
        subTotalPriceLbl.setText(String.valueOf(subTotal));
        totalPriceLbl.setText(String.valueOf(subTotal));
    }
}