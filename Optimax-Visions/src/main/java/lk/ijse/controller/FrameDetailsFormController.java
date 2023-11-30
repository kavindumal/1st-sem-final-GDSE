package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
        if (AddNewFrameFormController.countAF % 2 == 1) {
            AnchorPane pane = new AnchorPane();
            pane.setLayoutX(633);
            pane.setLayoutY(0);
            pane.setPrefWidth(413);
            pane.setPrefHeight(99);
            pane.setStyle("-fx-background-color: #1A4BCC; -fx-background-radius: 15; -fx-opacity: 0.5");
            frameDetailsPane.getChildren().add(pane);
            animatePaneTransition(pane);

            AddNewFrameFormController.countAF = 0;
        }
        setCellValueFactory();
        loadDetailsToTable();
    }

    private void animatePaneTransition(AnchorPane pane) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), pane);
        transition.setFromY(-pane.getPrefHeight());
        transition.setToY(0);
        transition.setOnFinished(event -> {
            ImageView imageView = new ImageView(new Image("img/icons/taskComplete.gif"));
            imageView.setLayoutX(657);
            imageView.setLayoutY(25);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            frameDetailsPane.getChildren().add(imageView);
            Label label1 = new Label("Added Successfully!");
            Label label2 = new Label("New frame added successfully.");
            label1.setLayoutX(719);
            label1.setStyle("-fx-font-size: 21px; -fx-font-weight: bold;");
            label2.setStyle("-fx-font-size: 16px");
            label2.setLayoutX(719);
            label1.setLayoutY(19);
            label2.setLayoutY(43);
            frameDetailsPane.getChildren().add(label1);
            frameDetailsPane.getChildren().add(label2);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                frameDetailsPane.getChildren().remove(pane);
                frameDetailsPane.getChildren().remove(imageView);
                frameDetailsPane.getChildren().remove(label1);
                frameDetailsPane.getChildren().remove(label2);
            });

            pause.play();
        });
        transition.play();
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