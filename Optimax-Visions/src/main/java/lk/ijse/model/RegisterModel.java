package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.RegisterDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class RegisterModel {
    public static boolean setDetails(RegisterDto registerDto) {
        boolean checkUsername = false;
        String[][] details= DBConnection.getDetails("user", 3);
        for (int i = 0; i < details.length; i++) {
            if (!details[i][0].equals(registerDto.getUsername())) {
                checkUsername = true;
                DBConnection.setDetails("INSERT INTO visioncare.user (username, password, email)\n" +
                        "VALUES ('"+ registerDto.getUsername() +"', '"+ BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt()) +"', '"+ registerDto.getEmailAddress() +"');\n" +
                        "\n");
            }
        }
        return checkUsername;
    }
}
