package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.LoginDto;
import lk.ijse.model.LoginModel;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {

    @FXML
    private JFXButton createNewAccBtn;

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
    void createNewAccBtnOnAction(ActionEvent event) {

    }

    @FXML
    void forgotPwBtnOnAction(ActionEvent event) {

    }

    @FXML
    void loginBtnOnAction(ActionEvent event) {
        boolean loginToSystem = LoginModel.loginToSystem(new LoginDto(usernameTxt.getText(), passwordTxt.getText()));
        if (loginToSystem) {
            loginPane.getChildren().clear();
            try {
                loginPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "username or password is not matched...").show();
        }
    }

}
