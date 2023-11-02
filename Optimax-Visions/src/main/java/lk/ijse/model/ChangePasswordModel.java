package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.ChangePasswordDto;

import java.sql.SQLException;

public class ChangePasswordModel {

    public void setDataToDatabase(ChangePasswordDto changePasswordDto, String email) throws SQLException {
        String[][] databaseDetailsOfUser = DbConnections.getDetails("user", 3);
        for (int i = 0; i < databaseDetailsOfUser.length; i++) {
            if (databaseDetailsOfUser[i][2].equals(email)) {
                DbConnections.setDetails("UPDATE visioncare.user t\n" +
                        "SET t.password = '"+ changePasswordDto.getPassword() +"' " +
                        "WHERE t.username LIKE '"+ databaseDetailsOfUser[i][0] +"' ESCAPE '#';\n" +
                        "\n");
            }
        }
    }

    public boolean checkPasswordEquality(ChangePasswordDto changePasswordDto) {
        if (changePasswordDto.getPassword().equals(changePasswordDto.getConfirmPassword())) {
            return true;
        }
        return false;
    }
}
