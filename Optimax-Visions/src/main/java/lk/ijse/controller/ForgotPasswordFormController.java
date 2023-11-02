package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.ForgotDto;
import lk.ijse.model.ForgotModel;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ForgotPasswordFormController {

    @FXML
    private AnchorPane forgotPane;

    @FXML
    private ImageView backImg;

    @FXML
    private ImageView alertImage2;

    @FXML
    private Rectangle emailRec;

    @FXML
    private TextField emailTxt;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private Label emailforgotErrorLabel;

    private ForgotModel forgotModel = new ForgotModel();
    @FXML
    void backOnAction(MouseEvent event) {
        forgotPane.getChildren().clear();
        try {
            forgotPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void submitBtnOnAction(ActionEvent event) throws IOException, SQLException {
        AlertSound alertSound = new AlertSound();
        if (forgotModel.checkEmailAvailability(new ForgotDto(emailTxt.getText()))) {
            emailRec.setStroke(Color.BLACK);
            alertImage2.setOpacity(0);
            emailforgotErrorLabel.setOpacity(0);
            int otp = forgotModel.generateNewOtp();
            emailforgotErrorLabel.setText("email address is can't find.Please try again.");
            if (forgotModel.getOtp(emailTxt.getText(), otp)){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/otpForm.fxml"));
                Parent root = loader.load();
                OtpFormController otpFormController = loader.getController();

                otpFormController.setDataFromSubmit(emailTxt.getText(), "forgot", otp);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                emailforgotErrorLabel.setText("Invalid email address. please check again.");
                alertSound.checkSounds(Sounds.INVALID);
                emailRec.setStroke(Color.RED);
                alertImage2.setOpacity(1);
                emailforgotErrorLabel.setOpacity(1);
            }
        } else {
            alertSound.checkSounds(Sounds.INVALID);
            emailRec.setStroke(Color.RED);
            alertImage2.setOpacity(1);
            emailforgotErrorLabel.setOpacity(1);
        }
    }
}
