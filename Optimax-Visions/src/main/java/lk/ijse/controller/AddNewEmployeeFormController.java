package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
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
import lk.ijse.dto.AddEmployeeDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.model.EmployeeModel;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.Objects;
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
    String destinationFolderPath = "C:\\Users\\Kavindu\\Documents\\GDSE 68\\1 st sem Final Project\\eye clinic\\software\\1st-sem-final-GDSE\\Optimax-Visions\\src\\main\\resources\\img\\profilePicOfEmployees";

    EmployeeModel model = new EmployeeModel();

    @FXML
    void addBtnOnAction(ActionEvent event) throws SQLException {
        String link = "";
        if (!profilePhotoLink.isEmpty()) {
            try {
                File sourceFile = new File(new URL(profilePhotoLink).toURI());
                Path destinationFolderPath = Paths.get(this.destinationFolderPath);
                if (!Files.exists(destinationFolderPath)) {
                    Files.createDirectories(destinationFolderPath);
                }

                String fileName = nameTxt.getText() + System.currentTimeMillis() + ".png";
                Path destinationFilePath = destinationFolderPath.resolve(fileName);
                link = "img/icons/" + fileName;
                Files.copy(sourceFile.toPath(), destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else link = "img/icons/profilePic.png";


        if (model.setEmployeeToDatabase(new AddEmployeeDto(nicNumberTxt.getText(), nameTxt.getText(), jobTitleComboBox.getValue(), dateOfBirthDP.getValue(), Integer.parseInt(telNoTxt.getText()), findBasicSalary(), link))) {
            addNewEmployeePane.getChildren().clear();
            try {
                addNewEmployeePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/employeeForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Double findBasicSalary() {
        return jobTitleComboBox.getValue().equals("c.helpers") ? 26000.00 : jobTitleComboBox.getValue().equals("cashier") ? 32000.00 : jobTitleComboBox.getValue().equals("IT operator") ? 35000.00 : jobTitleComboBox.getValue().equals("security") ? 22000.00 : 18000.00;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setCenterX(profilePhoto.getFitWidth() / 2);
        clip.setCenterY(profilePhoto.getFitHeight() / 2);
        clip.setRadius(Math.min(profilePhoto.getFitWidth() / 2, profilePhoto.getFitHeight() / 2));

        profilePhoto.setClip(clip);
        ObservableList<String> jobTitles = FXCollections.observableArrayList(
                "c.helpers",
                "cashier",
                "IT operator",
                "security",
                "cleaner"
        );

        jobTitleComboBox.setItems(jobTitles);
        jobTitleComboBox.setValue(jobTitles.get(0));
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