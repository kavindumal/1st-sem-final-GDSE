package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.LoginDto;
import org.mindrot.jbcrypt.BCrypt;

public class LoginModel {
    static String[][] details = DBConnection.getDetails("user", 3);
    public static boolean checkUsername(LoginDto loginDto) {
        boolean checkUsername = false;
        for (int i = 0; i < details.length; i++) {
            if (loginDto.getUsername().equals(details[i][0])) checkUsername = true;
        }
        return checkUsername;
    }

    public static boolean checkPassword(LoginDto loginDto) {
        boolean checkPassword = false;
        for (int i = 0; i < details.length; i++) {
            if (BCrypt.checkpw(loginDto.getPassword(), details[i][1])) checkPassword = true;
        }
        return checkPassword;
    }
}
