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
import lk.ijse.dto.LenseDto;
import lk.ijse.dto.tm.LenseTm;
import lk.ijse.model.FrameModel;
import lk.ijse.model.LenseModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LensesDetailsFormController implements Initializable {
    public static String id;
    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableView<LenseTm> lenseTbl;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> priceCol;

    @FXML
    private TableColumn<?, ?> qtyCol;

    @FXML
    private TableColumn<?, ?> removeCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> updateCol;

    @FXML
    private AnchorPane lensePane;

    @FXML
    void addNewLenseBtnOnAction(ActionEvent event) {
        lensePane.getChildren().clear();
        try {
            lensePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/addNewLenseForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("update"));
        removeCol.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllLenses();
    }

    private void loadAllLenses() {
        var model = new LenseModel();

        ObservableList<LenseTm> obList = FXCollections.observableArrayList();

        try {
            List<LenseDto> dtoList = model.getAllValues();

            for(LenseDto dto : dtoList) {
                dto.getUpdate().setOnAction(event -> {
                    try {
                        handleuButtonClicked(dto.getId());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                dto.getRemove().setOnAction(event -> handleButtonClicked(dto.getId()));
                obList.add(
                        new LenseTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getType(),
                                dto.getQty(),
                                dto.getPrice(),
                                dto.getUpdate(),
                                dto.getRemove()
                        )
                );
            }
            lenseTbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleuButtonClicked(String id) throws IOException, SQLException {
        LensesDetailsFormController.id = id;
        lensePane.getChildren().clear();
        try {
            lensePane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/updateLenseForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleButtonClicked(String id) {
        LenseModel model = new LenseModel();
        try {
            model.deleteDataFromDatabase(id);
            loadAllLenses();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
