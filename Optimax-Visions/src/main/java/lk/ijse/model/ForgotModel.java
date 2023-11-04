package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.ForgotDto;

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
}
