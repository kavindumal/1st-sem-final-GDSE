package lk.ijse.model;

import com.jfoenix.controls.JFXButton;
import lk.ijse.controller.PresGlassSellFormController;
import lk.ijse.db.DbConnections;
import lk.ijse.dto.FrameDetailsDto;
import lk.ijse.dto.FrameDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrameModel {
    public List<FrameDetailsDto> getAllValues() throws SQLException {
        Connection connection = DbConnections.getInstance().getConnection();

        String sql = "SELECT * FROM frame";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<FrameDetailsDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String type = resultSet.getString(3);
            String color = resultSet.getString(7);
            String material = resultSet.getString(8);
            String qty = resultSet.getString(9);
            String price = resultSet.getString(10);

            JFXButton updateBtn = new JFXButton("Update");
            updateBtn.setStyle("-fx-background-radius: 30; -fx-background-color: green; -fx-text-fill: white; -fx-font-size: 19px;");

            JFXButton removeBtn = new JFXButton("Remove");
            removeBtn.setStyle("-fx-background-radius: 30; -fx-background-color: Red; -fx-text-fill: white; -fx-font-size: 19px;");

            dtoList.add(new FrameDetailsDto(id,name,type,color,material,Integer.parseInt(qty),Double.parseDouble(price),updateBtn,removeBtn));
        }
        return dtoList;
    }

    public boolean deleteDataFromDatabase(String id) throws SQLException {
        return DbConnections.setDetails("DELETE\n" +
                "FROM visioncare.frame\n" +
                "WHERE frameId LIKE '"+ id +"' ESCAPE '#';\n" +
                "\n");
    }

    public String[][] getValues() throws SQLException {
        return DbConnections.getDetails("frame",11);
    }

    public boolean updateDetails(FrameDto frameDto, String photoLink) throws SQLException {
        return DbConnections.setDetails("UPDATE visioncare.frame t\n" +
                "SET t.frameName  = '"+ frameDto.getName() +"',\n" +
                "    t.frameType  = '"+ frameDto.getType() +"',\n" +
                "    t.wearGlass  = '"+ frameDto.getGlass() +"',\n" +
                "    t.faceShape  = '"+ frameDto.getFaceShape() +"',\n" +
                "    t.frameShape = '"+ frameDto.getFrameShape() +"',\n" +
                "    t.color      = '"+ frameDto.getColor() +"',\n" +
                "    t.material   = '"+ frameDto.getMaterial() +"',\n" +
                "    t.qtyOnHand  = "+ frameDto.getQtyOnHand() +",\n" +
                "    t.price      = "+ frameDto.getPrice() +",\n" +
                "    t.photo      = '"+ photoLink + "'\n" +
                "WHERE t.frameId LIKE '"+ frameDto.getId() +"' ESCAPE '#';\n" +
                "\n");
    }

    public boolean updateFrameModel() throws SQLException {
        return DbConnections.setDetails("UPDATE visioncare.frame t\n" +
                "SET t.qtyOnHand = "+ (PresGlassSellFormController.qty - 1) +"\n" +
                "WHERE t.frameId LIKE '"+ PresGlassSellFormController.frameId +"' ESCAPE '#';\n" +
                "\n");
    }
}