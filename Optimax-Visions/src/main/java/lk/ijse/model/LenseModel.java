package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.dto.LenseDetailsDto;
import lk.ijse.dto.LenseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LenseModel {
    public List<LenseDto> getAllValues() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM lense";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<LenseDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String type = resultSet.getString(3);
            int qty = resultSet.getInt(4);
            double price = resultSet.getDouble(5);

            JFXButton updateBtn = new JFXButton("Update");
            updateBtn.setStyle("-fx-background-radius: 30; -fx-background-color: green; -fx-text-fill: white; -fx-font-size: 19px;");

            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            dtoList.add(new LenseDto(id,name,type,qty,price,updateBtn,removeBtn));
        }
        return dtoList;
    }

    public boolean deleteDataFromDatabase(String id) throws SQLException {
        return DbConnections.setDetails("DELETE\n" +
                "FROM visioncare.lense\n" +
                "WHERE lenseId LIKE '"+ id +"' ESCAPE '#';\n" +
                "\n");
    }

    public String getLenseId() throws SQLException {
        String[][] lenseIds = DbConnections.getDetails("lense",5);
        if (lenseIds.length == 0) return "L0001";
        String lastPatientId = lenseIds[lenseIds.length - 1][0];

        int numericPart = Integer.parseInt(lastPatientId.replaceFirst("^L0*", ""));
        int incrementedNumericPart = numericPart + 1;

        return String.format("L%04d", incrementedNumericPart);
    }


    public boolean setLenseToDatabase(LenseDetailsDto dto) throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.lense (lenseId, name, typeFor, qtyOnHand, price)\n" +
                "VALUES ('"+ dto.getLenseId() +"', '"+ dto.getName() +"', '"+ dto.getTypeFor() +"', "+ dto.getQty() +", "+ dto.getPrice() +");\n" +
                "\n");
    }
}
