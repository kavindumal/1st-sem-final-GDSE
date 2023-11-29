package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
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
    void loginBtnOnAction(ActionEvent event) throws Exception {
        LoginModel loginModel = new LoginModel();
        if (loginModel.checkUsername(new LoginDto(usernameTxt.getText(), passwordTxt.getText()))) {
            usernameRec.setStroke(Color.BLACK);
            alertImage1.setOpacity(0);
            invalidusrOrPwLbl1.setOpacity(0);
            if (loginModel.checkPassword(new LoginDto(usernameTxt.getText(), passwordTxt.getText()))){
                displayLoadingAnimation(loginPane);

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> navigateToDashboard(loginPane)));
                timeline.play();
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

    private void navigateToDashboard(AnchorPane loginPane) {
        loginPane.getChildren().clear();
        try {
            Parent dashboard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/dashboardForm.fxml")));
            loginPane.getChildren().add(dashboard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayLoadingAnimation(AnchorPane loginPane) {
        Image loadingImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/icons/loading.gif")));
        ImageView loadingImageView = new ImageView(loadingImage);
        Label label = new Label("Loading");
        label.setStyle("-fx-font-size: 23px; -fx-text-fill: #607D8B");
        label.setLayoutX(910);
        label.setLayoutY(580);
        loadingImageView.setFitHeight(50);
        loadingImageView.setFitWidth(50);
        loadingImageView.setLayoutX(925);
        loadingImageView.setLayoutY(515);
        loginPane.getChildren().add(loadingImageView);
        loginPane.getChildren().add(label);
    }
}