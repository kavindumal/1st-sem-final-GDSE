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
import javafx.stage.StageStyle;
import lk.ijse.alert.AlertSound;
import lk.ijse.alert.Sounds;
import lk.ijse.dto.ForgotDto;
import lk.ijse.gmail.Gmailer;
import lk.ijse.model.ForgotModel;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

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
            int otp = generateNewOtp();
            emailforgotErrorLabel.setText("email address is can't find.Please try again.");
            if (getOtp(emailTxt.getText(), otp)){
//                openConfirmPage();
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

    public int generateNewOtp() {
        int otp;
        do {
            Random random = new Random();
            otp = random.nextInt(9999);
            if (otp > 1000) return otp;
        }while (true);
    }

    public boolean getOtp(String email, int otp) {
        boolean b1 = false;
        if (email.contains("@")){
            int index = email.indexOf("@");
            if (!email.substring(index + 1).equals("gmail.com")){
                return false;
            }
        } else {
            return false;
        }
        try {
            b1 = Gmailer.setEmailCom(email, otp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b1;
    }

    public void openConfirmPage() throws IOException {
        Parent rootNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/popup/resetPwPopupForm.fxml")));

        Scene scene = new Scene(rootNode);

        Stage stage = new Stage();
        stage.setOpacity(0.75);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

    }
}