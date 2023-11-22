package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lk.ijse.controller.popup.DeleteAccountConfirmFormController;
import lk.ijse.model.LoginModel;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsFormController implements Initializable {

    @FXML
    private PrefixSelectionComboBox<String> accountTypeComboBox;

    @FXML
    private ImageView profilePictureImgView;

    @FXML
    private AnchorPane settingsPane;

    @FXML
    void addNewAccountBtnOnAction(ActionEvent event) {
        settingsPane.getChildren().clear();
        try {
            settingsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/registerForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changePwBtnOnAction(ActionEvent event) {
        settingsPane.getChildren().clear();
        try {
            settingsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/forgotPasswordForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteAccountBtnOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/deleteAccountConfirmForm.fxml"));
        Parent root = loader.load();
        DeleteAccountConfirmFormController deleteAccountConfirmFormController = loader.getController();


        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        deleteAccountConfirmFormController.setStageDetails(stage);

        stage.show();
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/loginForm.fxml")))));
    }

    @FXML
    void uploadnewPhotoBtnOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a photo");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = new Stage();
        centerStage(stage);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            profilePictureImgView.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    private void centerStage(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2.0;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2.0;

        stage.setX(centerX);
        stage.setY(centerY);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String historyOfLogin = null;
        try {
            LoginModel model = new LoginModel();
            historyOfLogin = model.getHistoryOfLogin();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> accountType = null;
        if (historyOfLogin.equals("Administrator")) {
            accountType = FXCollections.observableArrayList(
                    "\tAdministrator",
                    "\tLocal"
            );
        } else {
            accountType = FXCollections.observableArrayList(
                    "\tLocal",
                    "\tAdministrator"
            );
        }

        accountTypeComboBox.setItems(accountType);
        accountTypeComboBox.setValue(accountType.get(0));
    }
}