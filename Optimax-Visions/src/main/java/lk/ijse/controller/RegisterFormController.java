package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.RegisterDto;
import lk.ijse.gmail.Gmailer;
import lk.ijse.model.RegisterModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

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

    int otp;

    @FXML
    public void backOnAction(MouseEvent event) {
        registerPane.getChildren().clear();
        try {
            registerPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void registerBtnOnAction(ActionEvent event) throws SQLException, IOException {
        AlertSound alertSound = new AlertSound();
        RegisterDto registerDto = new RegisterDto(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), conPwTxt.getText());
        register = new RegisterModel(registerDto);
        if (register.checkUsernameAvailability()){
            usernameRec111.setStroke(Color.BLACK);
            alertImage4.setOpacity(0);
            emailAddressCheckLbl.setOpacity(0);
            usernameCheckLbl.setOpacity(0);
            alertImage2.setOpacity(0);
            usernameRec.setStroke(Color.BLACK);
            if (!checkUsername(usernameTxt.getText())) {
                new Alert(Alert.AlertType.ERROR, "Invalid username").show();
            }else {
                if (checkEmailLong()){
                    usernameRec111.setStroke(Color.BLACK);
                    emailAddressCheckLbl.setOpacity(0);
                    alertImage4.setOpacity(0);
                    if (checkPasswordLength()){
                        passwordRec.setStroke(Color.BLACK);
                        alertImage3.setOpacity(0);
                        passwordLongLbl.setOpacity(0);
                        if (checkConfirmPassword()){
                            alertImsge.setOpacity(0);
                            conPwRec.setStroke(Color.BLACK);
                            confirmPwLbl.setOpacity(0);
                            otp = generateNewOtp();
                            if (getOtp()) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/otpForm.fxml"));
                                Parent root = loader.load();
                                OtpFormController otpFormController = loader.getController();

                                otpFormController.setDataFromRegister(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText(), otp, "create");

                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } else {
                                alertSound.checkSounds(Sounds.INVALID);
                                usernameRec111.setStroke(Color.RED);
                                alertImage4.setOpacity(1);
                                emailAddressCheckLbl.setOpacity(1);
                                emailAddressCheckLbl.setText("Please check your email address");
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
                    emailAddressCheckLbl.setText("email address required.");
                    alertSound.checkSounds(Sounds.INVALID);
                    emailTxt.requestFocus();
                    usernameRec111.setStroke(Color.RED);
                    emailAddressCheckLbl.setOpacity(1);
                    alertImage4.setOpacity(1);
                }
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            usernameCheckLbl.setOpacity(1.0);
            alertImage2.setOpacity(1.0);
            usernameRec.setStroke(Color.RED);
            usernameTxt.requestFocus();
        }
    }

    public boolean checkUsername(String text) {
        return Pattern.matches("^[A-Z][a-zA-Z0-9]*$", text);
    }

    public boolean getOtp() {
        boolean b1 = false;
        if (emailTxt.getText().contains("@")){
            int index = emailTxt.getText().indexOf("@");
            if (!emailTxt.getText().substring(index + 1).equals("gmail.com")){
                return false;
            }
        } else {
            return false;
        }
        try {
            b1 = Gmailer.setEmailCom(emailTxt.getText(), otp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b1;
    }


    public int generateNewOtp() {
        int otp;
        do {
            Random random = new Random();
            otp = random.nextInt(9999);
            if (otp > 1000) return otp;
        }while (true);
    }

    public boolean checkEmailLong(){
        return !emailTxt.getText().isEmpty();
    }

    public boolean checkConfirmPassword() {
        return passwordTxt.getText().equals(conPwTxt.getText());
    }

    public boolean checkPasswordLength() {
        return passwordTxt.getText().length() >= 8;
    }
}