package lk.ijse.controller;

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
import lk.ijse.dto.tm.PatientTm;
import lk.ijse.model.PatientModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PatientPageFormController implements Initializable {

    @FXML
    private AnchorPane patientFormPane;

    @FXML
    private TableColumn<?, ?> deleteeol;

    @FXML
    private TableColumn<?, ?> emailCol;

    @FXML
    private TableColumn<?, ?> familyCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableView<PatientTm> patientCol;

    @FXML
    private TableColumn<?, ?> telCol;

    @FXML
    private TableColumn<?, ?> updateCol;

    @FXML
    void viewAppointmentBtnOnAction(ActionEvent event) {
        patientFormPane.getChildren().clear();
        try {
            patientFormPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/appointmentForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadDetailsToTable();
    }

    private void loadDetailsToTable() {
        var model = new PatientModel();

        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientTm> dtoList = model.getAllData();

            for(PatientTm tm : dtoList) {
                tm.getUpdate().setOnAction(event -> {
                    handleUpdate(tm.getId());
                });
                tm.getDelete().setOnAction(event -> {
                    handleDelete(tm.getId());
                });
                obList.add(
                        new PatientTm(
                                tm.getId(),
                                tm.getName(),
                                tm.getEmail(),
                                tm.getFamily(),
                                tm.getTel(),
                                tm.getUpdate(),
                                tm.getDelete()
                        )
                );
            }
            patientCol.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDelete(String id) {
    }

    private void handleUpdate(String id) {

    }

    private void setCellValueFactory() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        familyCol.setCellValueFactory(new PropertyValueFactory<>("family"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("update"));
        deleteeol.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
}