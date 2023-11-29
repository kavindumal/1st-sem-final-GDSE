package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.ForgotDto;
import lk.ijse.dto.FrameDto;

import java.sql.SQLException;


public class ForgotModel {

    public boolean checkEmailAvailability(ForgotDto forgotDto) throws SQLException {
        String[][] detailsOfUsers = DbConnections.getDetails("user", 3);
        for (String[] detailsOfUser : detailsOfUsers) {
            if (detailsOfUser[2].equals(forgotDto.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public String getFrameId() throws SQLException {
        String[][] getFrameDetails = DbConnections.getDetails("frame",10);
        if (getFrameDetails.length == 0) return "F0001";
        String lastFrmaeId = getFrameDetails[getFrameDetails.length - 1][0];

        int numericPart = Integer.parseInt(lastFrmaeId.replaceFirst("^F0*", ""));
        int incrementedNumericPart = numericPart + 1;
        return String.format("F%04d", incrementedNumericPart);
    }

    public boolean setDetailsToDatabase(FrameDto frameDto, String photo) throws SQLException {
        return DbConnections.setDetails("INSERT INTO visioncare.frame (frameId, frameName, frameType, wearGlass, faceShape, frameShape, color, material,\n" +
                "                              qtyOnHand, price, photo)\n" +
                "VALUES ('"+ frameDto.getId() +"', '"+ frameDto.getName() +"', '"+ frameDto.getType() +"', '"+ frameDto.getGlass() +"', '"+ frameDto.getFaceShape() +"', '"+ frameDto.getFrameShape() +"', '"+ frameDto.getColor() +"', '"+ frameDto.getMaterial() +"', "+ frameDto.getQtyOnHand() +", "+ frameDto.getPrice() +",'"+ photo +"');\n" +
                "\n");
    }
}