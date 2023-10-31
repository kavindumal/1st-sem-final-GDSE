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
import lk.ijse.dto.LoginDto;
import lk.ijse.dto.RegisterDto;
import lk.ijse.model.LoginModel;
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
    private Rectangle usernameRec1;

    @FXML
    private Rectangle passwordRec;

    @FXML
    private Rectangle usernameRec11;

    @FXML
    private Rectangle usernameRec111;

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
//requestFocus();
    @FXML
    void registerBtnOnAction(ActionEvent event) {
        if (passwordTxt.getLength() > 8){
            if (passwordTxt.getText().equals(conPwTxt.getText())) {
                boolean checkAvailability = RegisterModel.setDetails(new RegisterDto(usernameTxt.getText(), emailTxt.getText(), passwordTxt.getText()));
                if (checkAvailability){
                    registerPane.getChildren().clear();
                    try {
                        registerPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/dashboardForm.fxml"))));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    usernameCheckLbl.setOpacity(1.0);
                    alertImage2.setOpacity(1.0);
                    usernameRec.setStroke(Color.RED);
                    usernameTxt.requestFocus();
                }
            } else {
                alertImsge.setOpacity(1.0);
                conPwRec.setStroke(Color.RED);
                confirmPwLbl.setOpacity(1.0);
            }
        } else {
            passwordTxt.requestFocus();
            conPwRec.setStroke(Color.RED);
            passwordRec.setStroke(Color.RED);
            alertImage3.setOpacity(1.0);
            passwordLongLbl.setOpacity(1.0);
        }
    }
}
