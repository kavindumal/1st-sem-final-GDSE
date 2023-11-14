package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.tm.TransactionTm;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> amountCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> timeCol;

    @FXML
    private TableColumn<?, ?> transactionIdCol;

    @FXML
    private AnchorPane transactionPane;

    @FXML
    private TableView<TransactionTm> transactionTable;

    @FXML
    private TableColumn<?, ?> transactionTypeCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllTransaction();
    }

    private void loadAllTransaction() {
        ObservableList<TransactionTm> obList = FXCollections.observableArrayList(
                new TransactionTm("T0001", "appointment", "20.02.50", "2023-11-14", "300"),
                new TransactionTm("T0002", "appointment", "20.02.10", "2023-11-14", "300"),
                new TransactionTm("T0003", "appointment", "19.58.22", "2023-11-14", "300")
        );

        transactionTable.setItems(obList);
    }

    private void setCellValueFactory() {
        transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        transactionTypeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
}
