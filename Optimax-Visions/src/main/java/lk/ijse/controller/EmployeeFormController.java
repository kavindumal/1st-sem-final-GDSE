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
import lk.ijse.dto.EmployeeDto;
import lk.ijse.dto.tm.EmployeeTm;
import lk.ijse.model.EmployeeModel;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> ageCol;

    @FXML
    private TableColumn<?, ?> deleteCol;

    @FXML
    private AnchorPane employeePane;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> jobTitleCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> telNoCol;

    @FXML
    private TableView<EmployeeTm> employeeTable;

    @FXML
    private TableColumn<?, ?> updateCol;

    @FXML
    private TableColumn<?, ?> viewCol;

    @FXML
    void addNewEmployeeBtnOnAction(ActionEvent event) {
        employeePane.getChildren().clear();
        try {
            employeePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/addNewEmployeeForm.fxml"))));
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
        var model = new EmployeeModel();

        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeeDto> dtoList = model.getAllValues();

            for(EmployeeDto dto : dtoList) {
                dto.getUpdateBtn().setOnAction(event -> {
                    handleUpdateBtn(dto.getNicNumber());
                });
                dto.getRemoveBtn().setOnAction(event -> {
                    try {
                        handleRemoveBtn(dto.getNicNumber());
                    } catch (IOException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                obList.add(
                        new EmployeeTm(
                                dto.getNicNumber(),
                                dto.getName(),
                                dto.getJobTitle(),
                                dto.getAge(),
                                dto.getTelNo(),
                                dto.getUpdateBtn(),
                                dto.getRemoveBtn(),
                                dto.getViewBtn()
                        )
                );
            }
            employeeTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRemoveBtn(String id) throws IOException, SQLException {
        EmployeeModel model = new EmployeeModel();
        String[][] dataFromEmployee = model.getDataFromEmployee();
        System.out.println("pako");
        for (int i = 0; i < dataFromEmployee.length; i++) {
            if (dataFromEmployee[i][0].equals(id)) {
                String photoPath = dataFromEmployee[i][6];

                Files.deleteIfExists(Paths.get(photoPath));
            }
        }
        if (model.deleteEmployeeFromDatabase(id)) {
            loadDetailsToTable();
        }
    }

    private void handleUpdateBtn(String id) {

    }

    private void setCellValueFactory() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("nicNumber"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        jobTitleCol.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        telNoCol.setCellValueFactory(new PropertyValueFactory<>("telNo"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("updateBtn"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
        viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
    }
}
