package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.ChangePasswordDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class ChangePasswordModel {

    public void setDataToDatabase(ChangePasswordDto changePasswordDto, String email) throws SQLException {
        String[][] databaseDetailsOfUser = DbConnections.getDetails("user", 3);
        for (int i = 0; i < databaseDetailsOfUser.length; i++) {
            if (databaseDetailsOfUser[i][2].equals(email)) {
                DbConnections.setDetails("UPDATE visioncare.user t\n" +
                        "SET t.password = '"+ BCrypt.hashpw(changePasswordDto.getPassword(), BCrypt.gensalt()) +"' " +
                        "WHERE t.username LIKE '"+ databaseDetailsOfUser[i][0] +"' ESCAPE '#';\n" +
                        "\n");
            }
        }
    }
}