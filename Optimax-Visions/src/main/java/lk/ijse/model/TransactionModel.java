package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.TransactionDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionModel {

    public List<TransactionDto> getAllValues() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM payment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TransactionDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String description = resultSet.getString(2);
            LocalTime time = resultSet.getTime(4).toLocalTime();
            LocalDate date = LocalDate.parse(resultSet.getString(5));
            Double amount = Double.valueOf(resultSet.getString(6));

            dtoList.add(new TransactionDto(id, description,time,date, amount));
        }
        return dtoList;
    }
}
