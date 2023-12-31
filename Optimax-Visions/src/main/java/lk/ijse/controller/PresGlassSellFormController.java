package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.dto.FrameDto;
import lk.ijse.dto.LenseDto;
import lk.ijse.dto.PatientDto;
import lk.ijse.dto.tm.PatientTm;
import lk.ijse.model.FrameModel;
import lk.ijse.model.LenseModel;
import lk.ijse.model.PatientModel;
import lk.ijse.model.PrescriptionModel;
import lk.ijse.prescriptionGeneratingCase.PrescriptionGenerator;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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
        PrescriptionModel prescriptionModel = new PrescriptionModel();
        resultLbl1.setText(PrescriptionResultFormController.dto.getName());
        lensePriceLbl.setText(calculateLensePrice());
        framePriceLbl.setText(getFramePrice());
        resultImg1.setImage(new Image(prescriptionModel.getImage(PrescriptionResultFormController.dto.getId())));
        subTotalPriceLbl.setText(String.valueOf(Double.parseDouble(lensePriceLbl.getText()) + Double.parseDouble(framePriceLbl.getText())));
        totalPriceLbl.setText(subTotalPriceLbl.getText());
        totalPrice = Double.parseDouble(totalPriceLbl.getText());
    }

    public static String frameId;
    public static int qty;

    private String getFramePrice() throws SQLException {
        FrameModel model = new FrameModel();
        List<FrameDetailsDto> allValues = model.getAllValues();
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getName().equals(resultLbl1.getText())) {
                frameId = allValues.get(i).getId();
                qty = allValues.get(i).getQty();
                return String.valueOf(allValues.get(i).getPrice());
            }
        }
        return null;
    }

    @FXML
    private AnchorPane resultPrescriptionPane;

    @FXML
    void placeOrderBtnOnAction(ActionEvent event) throws SQLException, JRException {
        PrescriptionModel prescriptionModel = new PrescriptionModel();
        if (prescriptionModel.updateValues()) {
            resultPrescriptionPane.getChildren().clear();
            try {
                Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/homeForm.fxml")));
                resultPrescriptionPane.getChildren().add(dashboard);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("lensePrice", lensePriceLbl.getText());
            parameters.put("framePrice", framePriceLbl.getText());
            parameters.put("prescriptionPrice", prescriptionPriceLbl.getText());
            parameters.put("specialFeatures", specialFeaturesPriceLbl.getText());
            parameters.put("subTotal", subTotalPriceLbl.getText());
            parameters.put("total", totalPriceLbl.getText());
            parameters.put("discount", discountPriceLbl.getText());
            parameters.put("balance", String.valueOf(Double.parseDouble(customerGivenPriceTxt.getText()) - Double.parseDouble(totalPriceLbl.getText())));
            parameters.put("sphRight", String.valueOf(PrescriptionDetailsFormController.sphereRight));
            parameters.put("cylRight", String.valueOf(PrescriptionDetailsFormController.cylRight));
            parameters.put("axisRight", String.valueOf(PrescriptionDetailsFormController.axisRight));
            parameters.put("addRight", String.valueOf(PrescriptionDetailsFormController.addRight));
            parameters.put("sphLeft", String.valueOf(PrescriptionDetailsFormController.sphereLeft));
            parameters.put("cylLeft", String.valueOf(PrescriptionDetailsFormController.cylLeft));
            parameters.put("axisLeft", String.valueOf(PrescriptionDetailsFormController.axisLeft));
            parameters.put("addLeft", String.valueOf(PrescriptionDetailsFormController.addLeft));
            parameters.put("patientName", getPatientName(PrescriptionModel.patientId));
            parameters.put("description", PrescriptionGenerator.detailsForRightEye + PrescriptionGenerator.detailsForLeftEye);
            parameters.put("pd", String.valueOf(PrescriptionDetailsFormController.pd));

            InputStream resourceAsStream = getClass().getResourceAsStream("/report/orderBill.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport, //compiled report
                            parameters,
                            new JREmptyDataSource() //database connection
                    );

            JasperViewer.viewReport(jasperPrint, false);
        } else {

        }

    }

    private String getPatientName(String patientId) throws SQLException {
        PatientModel patientModel = new PatientModel();
        List<PatientTm> allData = patientModel.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            if (allData.get(i).getId().equals(patientId)) {
                return allData.get(i).getName();
            }
        }
        return null;
    }

    public static String lenseIfLeft;
    public static double lenseQtyLeft;
    public static double lenseQtyRight;
    public static String lenseIfRight;
    public static double totalPrice;


    private String calculateLensePrice() throws SQLException {
        LenseModel lenseModel = new LenseModel();
        String[][] lenseDetails = lenseModel.getLenseDetails();
        double rightLensePrice = 0;
        double leftLensePrice = 0;
        for (int i = 0; i < lenseDetails.length; i++) {
            if (PrescriptionResultFormController.leftlenseValue.equals(lenseDetails[i][1])) {
                leftLensePrice = Double.parseDouble(lenseDetails[i][4]);
                lenseIfLeft = lenseDetails[i][0];
                lenseQtyLeft = Double.parseDouble(lenseDetails[i][3]);
            }
        }
        for (int i = 0; i < lenseDetails.length; i++) {
            if (PrescriptionResultFormController.rightlenseValue.equals(lenseDetails[i][1])) {
                rightLensePrice = Double.parseDouble(lenseDetails[i][4]);
                lenseIfRight = lenseDetails[i][0];
                lenseQtyRight = Double.parseDouble(lenseDetails[i][3]);
            }
        }
        return String.valueOf(rightLensePrice+leftLensePrice);
    }
}