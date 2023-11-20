package lk.ijse.controller.popup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.LoginModel;

import java.sql.SQLException;

public class DeleteAccountConfirmFormController {

    @FXML
    private AnchorPane deletePane;
    public Stage stage;
    @FXML
    void cancelBtnOnAction(ActionEvent event) {
        stage.close();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) throws SQLException {
        LoginModel model = new LoginModel();
        if (model.deleteAccount()) {
            stage.close();
        }
    }

    public void setStageDetails(Stage sStage) {
        this.stage = sStage;
    }
}