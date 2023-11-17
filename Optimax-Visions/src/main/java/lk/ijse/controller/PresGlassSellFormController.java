package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.kordamp.ikonli.javafx.FontIcon;

public class PresGlassSellFormController {

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

}
