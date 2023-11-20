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
import lk.ijse.dto.LoginDto;
import lk.ijse.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginFormController {

    @FXML
    private JFXButton createNewAccBtn;

    @FXML
    private Label invalidusrOrPwLbl;

    @FXML
    private Label invalidusrOrPwLbl1;

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

    @FXML
    private ImageView alertImage;

    @FXML
    private ImageView alertImage1;

    private final AlertSound alertSound = new AlertSound();

    @FXML
    void createNewAccBtnOnAction(ActionEvent event) {
        loginPane.getChildren().clear();
        try {
            loginPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/registerForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void forgotPwBtnOnAction(ActionEvent event) {
        loginPane.getChildren().clear();
        try {
            loginPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/forgotPasswordForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws SQLException {
        LoginModel loginModel = new LoginModel();

        if (loginModel.checkUsername(new LoginDto(usernameTxt.getText(), passwordTxt.getText()))) {
            usernameRec.setStroke(Color.BLACK);
            alertImage1.setOpacity(0);
            invalidusrOrPwLbl1.setOpacity(0);
            if (loginModel.checkPassword(new LoginDto(usernameTxt.getText(), passwordTxt.getText()))){
        loginPane.getChildren().clear();
        try {
            loginPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboardForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            } else {
                alertSound.checkSounds(Sounds.INVALID);
                pwRec.setStroke(Color.RED);
                alertImage.setOpacity(1);
                invalidusrOrPwLbl.setOpacity(1.0);
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            alertImage1.setOpacity(1);
            usernameRec.setStroke(Color.RED);
            invalidusrOrPwLbl1.setOpacity(1);
        }
    }
}