package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.TransactionDto;
import lk.ijse.dto.tm.TransactionTm;
import lk.ijse.model.TransactionModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
        var model = new TransactionModel();

        ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

        try {
            List<TransactionDto> dtoList = model.getAllValues();

            for(TransactionDto dto : dtoList) {
                obList.add(
                        new TransactionTm(
                                dto.getTransactionId(),
                                dto.getTransactionType(),
                                dto.getTime(),
                                dto.getDate(),
                                dto.getAmount()
                        )
                );
            }
            transactionTable.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        transactionIdCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        transactionTypeCol.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
}
