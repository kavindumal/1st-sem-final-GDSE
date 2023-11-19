package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.dto.tm.FrameDetailsTm;
import lk.ijse.model.FrameModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FrameDetailsFormController implements Initializable {
    @FXML
    public AnchorPane frameDetailsPane;

    @FXML
    private TableView<FrameDetailsTm> FrameTbl;

    @FXML
    private JFXButton addNewFrameBtn;

    @FXML
    private TableColumn<?, ?> colorCol;

    @FXML
    private TableColumn<?, ?> frameIdCol;

    @FXML
    private TableColumn<?, ?> materialCol;

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

    public static String id;

    @FXML
    void addNewFrameBtnOnAction(ActionEvent event) {
        frameDetailsPane.getChildren().clear();
        try {
            frameDetailsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/addNewFrameForm.fxml"))));
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
        var model = new FrameModel();

        ObservableList<FrameDetailsTm> obList = FXCollections.observableArrayList();

        try {
            List<FrameDetailsDto> dtoList = model.getAllValues();

            for(FrameDetailsDto dto : dtoList) {
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
                        new FrameDetailsTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getType(),
                                dto.getColor(),
                                dto.getMaterial(),
                                dto.getQty(),
                                dto.getPrice(),
                                dto.getUpdate(),
                                dto.getRemove()
                        )
                );
            }
            FrameTbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleuButtonClicked(String id) throws IOException, SQLException {
        FrameDetailsFormController.id = id;
        frameDetailsPane.getChildren().clear();
        try {
            frameDetailsPane.getChildren().add(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/updateFrameForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleButtonClicked(String id) {
        FrameModel model = new FrameModel();
        try {
            model.deleteDataFromDatabase(id);
            loadDetailsToTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        frameIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        colorCol.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialCol.setCellValueFactory(new PropertyValueFactory<>("material"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        updateCol.setCellValueFactory(new PropertyValueFactory<>("update"));
        removeCol.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }
}