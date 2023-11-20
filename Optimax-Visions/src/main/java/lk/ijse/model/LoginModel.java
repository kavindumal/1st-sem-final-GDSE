package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.LoginDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginModel {
    public String[][] details = DbConnections.getDetails("user", 4);

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

    public boolean checkPassword(LoginDto loginDto) throws SQLException {
        boolean checkPassword = false;
        for (String[] detail : details) {
            if (detail[0].equals(loginDto.getUsername()))
                if (BCrypt.checkpw(loginDto.getPassword(), detail[1])) {
                    checkPassword = true;
                    DbConnections.setDetails("INSERT INTO visioncare.loginhistory (username, time, date)\n" +
                            "VALUES ('"+ loginDto.getUsername() +"', '"+ LocalTime.now() +"', '"+ LocalDate.now() +"');\n" +
                            "\n");
                }
        }
        return checkPassword;
    }

    public boolean deleteAccount() throws SQLException {
        String[][] details1 = DbConnections.getDetails("loginHistory", 4);

        return DbConnections.setDetails("DELETE\n" +
                "FROM visioncare.user\n" +
                "WHERE username LIKE '"+ details1[details1.length-1][0] +"' ESCAPE '#';\n" +
                "\n");
    }

    public String getHistoryOfLogin() throws SQLException {
        String[][] details1 = DbConnections.getDetails("loginHistory", 3);
        for (int i = 0; i < details.length; i++) {
            if (details[i][0].equals(details1[details1.length - 1][0])) {
                return "Administrator";
            }
        }
        return "Local";
    }
}