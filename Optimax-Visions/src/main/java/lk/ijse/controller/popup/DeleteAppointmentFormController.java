package lk.ijse.controller.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.controller.AppointmentFormController;
import lk.ijse.model.AddNewAppointmentModel;

import java.sql.SQLException;

public class DeleteAppointmentFormController {
    @FXML
    private AnchorPane deletePane;
    public Stage stage;
    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) throws SQLException {
        AddNewAppointmentModel model = new AddNewAppointmentModel();
        if (model.removeAppointment(AppointmentFormController.appointmentId)) {
            stage.close();
        }
    }

    public void setStageDetails(Stage sStage) {
        this.stage = sStage;
    }
}