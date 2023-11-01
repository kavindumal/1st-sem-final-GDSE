package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.OtpVerificationDto;
import lk.ijse.model.OtpVerificationModel;
import lk.ijse.model.RegisterModel;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.EventHandler;

public class OtpVerificationFormController implements Initializable {
    @FXML
    private AnchorPane otpCheckPane;

    @FXML
    private TextField otpField1Txt;

    @FXML
    private TextField otpField2Txt;

    @FXML
    private TextField otpField3Txt;

    @FXML
    private TextField otpField4Txt;

    @FXML
    private JFXButton resendOtpBtn;

    @FXML
    private JFXButton vrfBtn;
    int count = 0;
    int otp;

    @FXML
    void resendOtpBtnOnAction(ActionEvent event) {

    }

    @FXML
    void vrfBtnOnAction(ActionEvent event) throws IOException {
        String[][] dbDetails = DBConnection.getDetails("tempgmailotp",2);

        int num1 = Integer.parseInt(otpField1Txt.getText());
        int num2 = Integer.parseInt(otpField2Txt.getText());
        int num3 = Integer.parseInt(otpField3Txt.getText());
        int num4 = Integer.parseInt(otpField4Txt.getText());
        int total = num1 * 1000 + num2 * 100 + num3 * 10 + num4;

        if (total == Integer.parseInt(dbDetails[0][1])){
            OtpVerificationModel.setDetails();
            otpCheckPane.getChildren().clear();
            otpCheckPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/newPasswordForm.fxml"))));
        } else {
            System.out.println("wrong huttoooooo");
        }

    }

    @FXML
    void keyPressedNow(KeyEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OtpVerificationModel otpVerificationModel = new OtpVerificationModel(new OtpVerificationDto(otpField1Txt.getText(), otpField2Txt.getText(), otpField3Txt.getText(), otpField4Txt.getText()));
        if (count < 1) {
            count++;
            otpVerificationModel.sendEmail();
        }

        otpField1Txt.addEventFilter(KeyEvent.KEY_TYPED, numericOnlyFilter);

        otpField2Txt.addEventFilter(KeyEvent.KEY_TYPED, numericOnlyFilter);

        otpField3Txt.addEventFilter(KeyEvent.KEY_TYPED, numericOnlyFilter);

        otpField4Txt.addEventFilter(KeyEvent.KEY_TYPED, numericOnlyFilter);

        otpField1Txt.setOnKeyReleased(event -> {
            String input = otpField1Txt.getText().trim();
            if (input.length() == 1) {
                otpField2Txt.requestFocus();
            } else if (input.length() == 0 && event.getCode() == KeyCode.BACK_SPACE) {
                otpField1Txt.clear();
                otpField1Txt.requestFocus();
            }
        });

        otpField2Txt.setOnKeyReleased(event -> {
            String input = otpField2Txt.getText().trim();
            if (input.length() == 1) {
                otpField3Txt.requestFocus();
            } else if (input.length() == 0 && event.getCode() == KeyCode.BACK_SPACE) {
                otpField1Txt.requestFocus();
            }
        });

        otpField3Txt.setOnKeyReleased(event -> {
            String input = otpField3Txt.getText().trim();
            if (input.length() == 1) {
                otpField4Txt.requestFocus();
            } else if (input.length() == 0 && event.getCode() == KeyCode.BACK_SPACE) {
                otpField2Txt.requestFocus();
            }
        });

        otpField4Txt.setOnKeyReleased(event -> {
            String input = otpField4Txt.getText().trim();
            if (input.length() == 0 && event.getCode() == KeyCode.BACK_SPACE) {
                otpField3Txt.requestFocus();
            }
        });

    }
    private final EventHandler<KeyEvent> numericOnlyFilter = event -> {
        char inputChar = event.getCharacter().charAt(0);

        if (!Character.isDigit(inputChar) && inputChar != '\b') {
            event.consume();
        }
    };
}
