package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.LoginDto;
import lk.ijse.model.LoginModel;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {

    @FXML
    private JFXButton createNewAccBtn;

    @FXML
    private Label invalidusrOrPwLbl;

    @FXML
    private JFXButton forgotPwBtn;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Rectangle pwRec;

    @FXML
    private Rectangle usernameRec;

    private AlertSound alertSound = new AlertSound();

    @FXML
    void createNewAccBtnOnAction(ActionEvent event) {

    }

    @FXML
    void forgotPwBtnOnAction(ActionEvent event) {

    }

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        boolean checkUsername = LoginModel.checkUsername(new LoginDto(usernameTxt.getText(), passwordTxt.getText()));
        boolean checkPassword = LoginModel.checkPassword(new LoginDto(usernameTxt.getText(), passwordTxt.getText()));

        if (checkUsername) {
            if (checkPassword){
                loginPane.getChildren().clear();
                try {
                    loginPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboardForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                alertSound.checkSounds(Sounds.INVALID);
                pwRec.setStroke(Color.RED);
                invalidusrOrPwLbl.setText("password is not matched for username");
                invalidusrOrPwLbl.setOpacity(1.0);
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            pwRec.setStroke(Color.RED);
            usernameRec.setStroke(Color.RED);
            invalidusrOrPwLbl.setOpacity(1);
        }
    }

}
