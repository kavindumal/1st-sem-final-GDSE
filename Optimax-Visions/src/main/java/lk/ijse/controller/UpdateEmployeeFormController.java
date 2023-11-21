package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import lk.ijse.model.EmployeeModel;
import lombok.SneakyThrows;
import org.controlsfx.control.PrefixSelectionComboBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateEmployeeFormController implements Initializable {

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
    private ImageView profilePhoto;

    @FXML
    private TextField telNoTxt;

    @FXML
    private AnchorPane updatePane;

    @FXML
    void profilePhotoOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void profilePhotoOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void profilePhotoOnMouseExited(MouseEvent event) {

    }

    @FXML
    void updateBtnOnAction(ActionEvent event) {

    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Circle clip = new Circle();
        clip.setCenterX(profilePhoto.getFitWidth() / 2);
        clip.setCenterY(profilePhoto.getFitHeight() / 2);
        clip.setRadius(Math.min(profilePhoto.getFitWidth() / 2, profilePhoto.getFitHeight() / 2));

        profilePhoto.setClip(clip);
        nicNumberTxt.setText(EmployeeFormController.id);
        EmployeeModel model = new EmployeeModel();
        String[][] dataFromEmployee = model.getDataFromEmployee();
        for (int i = 0; i < dataFromEmployee.length; i++) {
            if (dataFromEmployee[i][0].equals(EmployeeFormController.id)) {
                nameTxt.setText(dataFromEmployee[i][1]);
                setValuesToObservableList(dataFromEmployee[i][2]);
                dateOfBirthDP.setValue(LocalDate.parse(dataFromEmployee[i][3]));
                telNoTxt.setText(dataFromEmployee[i][4]);
                profilePhoto.setImage(new Image("/" +dataFromEmployee[i][6]));
            }
        }
    }

    public void setValuesToObservableList(String s){
        ObservableList<String> jobTitles = FXCollections.observableArrayList(
                "c.helpers",
                "cashier",
                "IT operator",
                "security",
                "cleaner"
        );

        jobTitleComboBox.setItems(jobTitles);
        jobTitleComboBox.setValue(s.equals("c.helpers") ? jobTitles.get(0) : s.equals("cashier") ? jobTitles.get(1) : s.equals("IT operator") ? jobTitles.get(2) : s.equals("security") ? jobTitles.get(3) : jobTitles.get(4));
    }
}
