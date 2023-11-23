package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.AppointmentTDto;
import lk.ijse.dto.tm.AppointmentTm;
import lk.ijse.model.AddNewAppointmentModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AppointmentFormController implements Initializable {

    @FXML
    private AnchorPane appointmentPane;

    @FXML
    private TableView<AppointmentTm> appointmentTbl;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> deleteRecordCol;

    @FXML
    private TableColumn<?, ?> doctorNameCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> patientNameCol;

    @FXML
    private TableColumn<?, ?> problemCol;

    @FXML
    private TableColumn<?, ?> timeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadDetailsToTable();
    }

    private void loadDetailsToTable() {
        var model = new AddNewAppointmentModel();

        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentTDto> dtoList = model.getAllData();

            for(AppointmentTDto dto : dtoList) {
                dto.getDeleteRecord().setOnAction(event -> {
                    handleDeleteBtn(dto.getId());
                });
                obList.add(
                        new AppointmentTm(
                                dto.getId(),
                                dto.getTime(),
                                dto.getDate(),
                                dto.getProblem(),
                                dto.getDoctorName(),
                                dto.getPatientName(),
                                dto.getDeleteRecord()
                        )
                );
            }
            appointmentTbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeleteBtn(String id) {
    }

    private void setCellValueFactory() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        patientNameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        deleteRecordCol.setCellValueFactory(new PropertyValueFactory<>("deleteRecord"));
    }
}
