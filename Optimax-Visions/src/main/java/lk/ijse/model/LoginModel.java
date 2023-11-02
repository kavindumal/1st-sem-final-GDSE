package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.LoginDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class LoginModel {
    public String[][] details = DbConnections.getDetails("user", 3);

    public LoginModel() throws SQLException {
    }

    public boolean checkUsername(LoginDto loginDto) {
        boolean checkUsername = false;
        for (int i = 0; i < details.length; i++) {
            if (loginDto.getUsername().equals(details[i][0])) checkUsername = true;
        }
        return checkUsername;
    }

    public boolean checkPassword(LoginDto loginDto) {
        boolean checkPassword = false;
        for (int i = 0; i < details.length; i++) {
            if (details[i][0].equals(loginDto.getUsername())) if (BCrypt.checkpw(loginDto.getPassword(), details[i][1])) checkPassword = true;
        }
        return checkPassword;
    }
}
