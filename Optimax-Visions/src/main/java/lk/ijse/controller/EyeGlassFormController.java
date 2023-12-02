package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.tm.PrescriptionTm;
import lk.ijse.model.EmployeeModel;
import lk.ijse.model.PrescriptionModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EyeGlassFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> PatientNameCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> deleteRecordCol;

    @FXML
    private AnchorPane eyGlassPane;

    @FXML
    private JFXButton frameDetailsBtn;

    @FXML
    private TableColumn<?, ?> frameTypeCol;

    @FXML
    private TableColumn<?, ?> glassIdCol;

    @FXML
    private JFXButton lenseDetailsBtn;

    @FXML
    private TableView<PrescriptionTm> orderedGlassesTbl;

    @FXML
    private TableColumn<?, ?> patientIdCol;

    @FXML
    private TableColumn<?, ?> timeCol;


    @FXML
    void frameDetailsBtnOnAction(ActionEvent event) {
        eyGlassPane.getChildren().clear();
        try {
            eyGlassPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/frameDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lenseDetailsBtnOnAction(ActionEvent event) {
        eyGlassPane.getChildren().clear();
        try {
            eyGlassPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/lensesDetailsForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        setCellValueFactory();
//        loadDetailsToTable();
    }

    private void setCellValueFactory() {
        glassIdCol.setCellValueFactory(new PropertyValueFactory<>("glassId"));
        frameTypeCol.setCellValueFactory(new PropertyValueFactory<>("frameType"));
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        PatientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        deleteRecordCol.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void loadDetailsToTable() {
        var model = new PrescriptionModel();

        ObservableList<PrescriptionTm> obList = FXCollections.observableArrayList();

        try {
            List<PrescriptionTm> dtoList = model.getAllValues();

            for(PrescriptionTm dto : dtoList) {
                dto.getButton().setOnAction(event -> {
                    try {
                        handleRemoveBtn(dto.getPatientId());
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                obList.add(
                        new PrescriptionTm(
                                dto.getLenseName(),
                                dto.getFrameType(),
                                dto.getPatientId(),
                                dto.getPatientName(),
                                dto.getTime(),
                                dto.getDate(),
                                dto.getButton()
                        )
                );
            }
            orderedGlassesTbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRemoveBtn(String id) throws IOException, SQLException {
        EmployeeModel model = new EmployeeModel();
        String[][] dataFromEmployee = model.getDataFromEmployee();
//        for (int i = 0; i < dataFromEmployee.length; i++) {
//            if (dataFromEmployee[i][0].equals(id)) {
//                String photoPath = dataFromEmployee[i][6];
//
//                Files.deleteIfExists(Paths.get(photoPath));
//            }
//        }
        if (model.deleteEmployeeFromDatabase(id)) {
            loadDetailsToTable();
        }
    }
}
