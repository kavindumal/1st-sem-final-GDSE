package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyEvent;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.OtpDto;
import lk.ijse.dto.RegisterDto;
import lk.ijse.model.OtpModel;
import lk.ijse.model.RegisterModel;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class OtpFormController implements Initializable{

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
    @FXML
    private ImageView alertImage1;

    @FXML
    private Label invalidLbl;

    public String username;
    public String password;
    public String email;
    public int otp;
    public OtpModel otpModel = new OtpModel();
    @FXML
    void resendOtpBtnOnAction(ActionEvent event) {

    }

    @FXML
    void vrfBtnOnAction(ActionEvent event) {
        if (otpModel.verifyOto(new OtpDto(otpField1Txt.getText(), otpField2Txt.getText(), otpField3Txt.getText(), otpField4Txt.getText()), otp)){
            if (otpModel.setDetailsToDatabase(username, password, email)) {
                otpCheckPane.getChildren().clear();
                try {
                    otpCheckPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            AlertSound alertSound = new AlertSound();
            alertSound.checkSounds(Sounds.INVALID);
            invalidLbl.setOpacity(1);
            alertImage1.setOpacity(1);
        }
    }

    @FXML
    void keyPressedNow(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void setDataFromRegister(String username, String email, String passwordTxtText, int otp) {
        this.username = username;
        this.email = email;
        this.password = passwordTxtText;
        this.otp = otp;
    }
}