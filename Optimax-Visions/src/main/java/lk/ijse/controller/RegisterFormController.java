package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lk.ijse.dto.LoginDto;
import lk.ijse.model.LoginModel;

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
    private Rectangle usernameRec11;

    @FXML
    private Rectangle usernameRec111;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Label confirmPwLbl;

    @FXML
    private Rectangle conPwRec;

    @FXML
    private ImageView alertImsge;

    @FXML
    void registerBtnOnAction(ActionEvent event) {
        if (passwordTxt.getText().equals(conPwTxt.getText())) {

        } else {
            alertImsge.setOpacity(1.0);
            conPwRec.setStroke(Color.RED);
            confirmPwLbl.setOpacity(1.0);
        }
    }

}
