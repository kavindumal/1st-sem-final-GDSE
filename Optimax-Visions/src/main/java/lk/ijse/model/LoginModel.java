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
        for (String[] detail : details) {
            if (loginDto.getUsername().equals(detail[0])) {
                checkUsername = true;
                break;
            }
        }
        return checkUsername;
    }

    public boolean checkPassword(LoginDto loginDto) {
        boolean checkPassword = false;
        for (String[] detail : details) {
            if (detail[0].equals(loginDto.getUsername()))
                if (BCrypt.checkpw(loginDto.getPassword(), detail[1])) checkPassword = true;
        }
        return checkPassword;
    }
}