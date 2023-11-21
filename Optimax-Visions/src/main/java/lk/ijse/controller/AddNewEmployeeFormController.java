package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewEmployeeFormController implements Initializable {

    @FXML
    private JFXButton addBtn;

    @FXML
    private AnchorPane addNewEmployeePane;

    @FXML
    private DatePicker dateOfBirthDP;

    @FXML
    private PrefixSelectionComboBox<String> jobTitleComboBox;

    @FXML
    private Rectangle lenseNameRec;

    @FXML
    private Rectangle lenseNameRec1;

    @FXML
    private Rectangle lenseNameRec111;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField nicNumberTxt;

    @FXML
    private TextField telNoTxt;

    @FXML
    private ImageView profilePhoto;

    String profilePhotoLink = "";

    @FXML
    void addBtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setCenterX(profilePhoto.getFitWidth() / 2);  // Set the center of the circle to half of the ImageView width
        clip.setCenterY(profilePhoto.getFitHeight() / 2); // Set the center of the circle to half of the ImageView height
        clip.setRadius(Math.min(profilePhoto.getFitWidth() / 2, profilePhoto.getFitHeight() / 2)); // Set the radius to half of the ImageView size

        // Set the clip on the ImageView
        profilePhoto.setClip(clip);
        ObservableList<String> jobTitles = FXCollections.observableArrayList(
                "c.helpers",
                "cashier",
                "IT team",
                "security",
                "cleaner"
        );

        jobTitleComboBox.setItems(jobTitles);
        jobTitleComboBox.setValue(jobTitles.get(0));
    }

    private Circle createClip() {
        Circle clip = new Circle();
        clip.setCenterX(50);  // Set the center of the circle to half of the ImageView width
        clip.setCenterY(50);  // Set the center of the circle to half of the ImageView height
        clip.setRadius(50);   // Set the radius to half of the ImageView size
        return clip;
    }

    @FXML
    void profilePhotoOnMouseClicked(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a photo");

        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        Stage stage = new Stage();
        centerStage(stage);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profilePhotoLink = selectedFile.toURI().toString();
            profilePhoto.setImage(image);
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

    @FXML
    void profilePhotoOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void profilePhotoOnMouseExited(MouseEvent event) {

    }
}