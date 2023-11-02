package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import lk.ijse.dto.ChangePasswordDto;
import lk.ijse.model.ChangePasswordModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ChangePasswordFormController {

    @FXML
    private ImageView alertImage;

    @FXML
    private ImageView alertImage1;

    @FXML
    private ImageView backImg;

    @FXML
    private Rectangle conPwRec;

    @FXML
    private Label confirmPasswordErrorLbl;

    @FXML
    private TextField confirmPasswordTxt;

    @FXML
    private Label newPasswordErrorLbl;

    @FXML
    private TextField newPasswordTxt;

    @FXML
    private Rectangle newPwRec;

    @FXML
    private JFXButton resetPwBtn;

    @FXML
    private AnchorPane resetPwPane;

    public ChangePasswordModel changePasswordModel;
    public String email;
    @FXML
    void backOnAction(MouseEvent event) {
        resetPwPane.getChildren().clear();
        try {
            resetPwPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void resetPwBtnOnAction(ActionEvent event) throws SQLException {
        ChangePasswordModel changePasswordModel = new ChangePasswordModel();
        if (changePasswordModel.checkPasswordEquality(new ChangePasswordDto(newPasswordTxt.getText(), confirmPasswordTxt.getText()))) {
            changePasswordModel.setDataToDatabase(new ChangePasswordDto(newPasswordTxt.getText(), confirmPasswordTxt.getText()),email);
        }
    }

    public void setDateForChangePassword(String email) {
        this.email = email;
    }
}