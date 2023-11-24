package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.controller.popup.DeleteAppointmentFormController;
import lk.ijse.dto.tm.AppointmentTm;
import lk.ijse.model.AddNewAppointmentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadPoolExecutor;

public class AppointmentFormController implements Initializable {

    @FXML
    private AnchorPane appointmentPane;

    @FXML
    private ImageView refreshBtn;

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

    public static String appointmentId = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadDetailsToTable();
    }

    private void loadDetailsToTable() {
        var model = new AddNewAppointmentModel();

        ObservableList<AppointmentTm> obList = FXCollections.observableArrayList();

        try {
            List<AppointmentTm> dtoList = model.getAllData();

            for(AppointmentTm tm : dtoList) {
                tm.getDeleteRecord().setOnAction(event -> {
                    try {
                        handleDeleteBtn(tm.getId());
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                obList.add(
                        new AppointmentTm(
                                tm.getId(),
                                tm.getTime(),
                                tm.getDate(),
                                tm.getProblem(),
                                tm.getDoctorName(),
                                tm.getPatientName(),
                                tm.getDeleteRecord()
                        )
                );
            }
            appointmentTbl.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleDeleteBtn(String id) throws SQLException, IOException {
        appointmentId = id;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/popup/deleteAppointmentForm.fxml"));
        Parent root = loader.load();
        DeleteAppointmentFormController changePasswordFormController = loader.getController();
        Stage stage = new Stage();
        changePasswordFormController.setStageDetails(stage);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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

    @FXML
    void refreshBtnClicked(MouseEvent event) {
        refreshBtn.setImage(new Image("img/icons/refreshAnimatod.gif"));

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), e -> {
            refreshBtn.setImage(new Image("img/icons/refresh.png"));
            loadDetailsToTable();
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }

}
