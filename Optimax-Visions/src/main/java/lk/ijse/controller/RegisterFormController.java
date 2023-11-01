package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.RegisterDto;
import lk.ijse.model.OtpVerificationModel;
import lk.ijse.model.RegisterModel;

import java.io.IOException;
import java.util.Objects;

public class RegisterFormController {

    @FXML
    private PasswordField conPwTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private JFXButton registerBtn;

    @FXML
    private Rectangle usernameRec;

    @FXML
    private Rectangle usernameRec111;

    @FXML
    private Rectangle passwordRec;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Label confirmPwLbl;

    @FXML
    private Label usernameCheckLbl;

    @FXML
    private Label passwordLongLbl;

    @FXML
    private Rectangle conPwRec;

    @FXML
    private ImageView alertImsge;

    @FXML
    private ImageView alertImage2;

    @FXML
    private ImageView alertImage3;

    @FXML
    private AnchorPane registerPane;

    @FXML
    private ImageView alertImage4;

    @FXML
    private Label emailAddressCheckLbl;

    public RegisterModel register;

    @FXML
    void registerBtnOnAction(ActionEvent event) {
        AlertSound alertSound = new AlertSound();
        register = new RegisterModel(new RegisterDto(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), conPwTxt.getText()));
        if (register.checkUsernameAvailability()){
            usernameCheckLbl.setOpacity(0);
            alertImage2.setOpacity(0);
            usernameRec.setStroke(Color.BLACK);
            if (register.checkEmailLong()){
                usernameRec111.setStroke(Color.BLACK);
                emailAddressCheckLbl.setOpacity(0);
                alertImage4.setOpacity(0);
                if (register.checkPasswordLength()){
                    passwordRec.setStroke(Color.BLACK);
                    alertImage3.setOpacity(0);
                    passwordLongLbl.setOpacity(0);
                    if (register.checkConfirmPassword()){
                        register.setValues();
                        registerPane.getChildren().clear();
                        try {
                            registerPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/otpVerificationForm.fxml"))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } finally {
                            new OtpVerificationModel(new RegisterDto(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), conPwTxt.getText()));
                        }
                    } else {
                        alertSound.checkSounds(Sounds.INVALID);
                        alertImsge.setOpacity(1);
                        conPwRec.setStroke(Color.RED);
                        confirmPwLbl.setOpacity(1);
                    }
                } else {
                    alertSound.checkSounds(Sounds.INVALID);
                    passwordTxt.requestFocus();
                    passwordRec.setStroke(Color.RED);
                    alertImage3.setOpacity(1);
                    passwordLongLbl.setOpacity(1);
                }
            } else {
                alertSound.checkSounds(Sounds.INVALID);
                emailTxt.requestFocus();
                usernameRec111.setStroke(Color.RED);
                emailAddressCheckLbl.setOpacity(1);
                alertImage4.setOpacity(1);
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            usernameCheckLbl.setOpacity(1.0);
            alertImage2.setOpacity(1.0);
            usernameRec.setStroke(Color.RED);
            usernameTxt.requestFocus();
        }
    }
}