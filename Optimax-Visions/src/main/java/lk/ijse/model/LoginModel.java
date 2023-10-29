package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.LoginDto;
import org.mindrot.jbcrypt.BCrypt;

public class LoginModel {
    public static boolean loginToSystem(LoginDto loginDto) {
        boolean loginToSystem = false;
        String[][] details = DBConnection.getDetails("user", 3);
        for (int i = 0; i < details.length; i++) {
            if (loginDto.getUsername().equals(details[i][0])){
                if (BCrypt.checkpw(loginDto.getPassword(), details[i][1])){
                    loginToSystem = true;
                }
            }
        }
        return loginToSystem;
    }
}
