package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.RegisterDto;

import java.sql.SQLException;

public class RegisterModel {
    public RegisterDto registerDto;
    public RegisterModel(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }

    public boolean checkUsernameAvailability() throws SQLException {
        String[][] details= DbConnections.getDetails("user", 3);
        boolean b = false;
        if (details.length == 0) {
            return true;
        } else {
            for (int i = 0; i < details.length; i++) {
                if (!details[i][0].equals(registerDto.getUsername())) {
                    b = true;
                }
                else {
                    return false;
                }
            }
        }
        return b;
    }
}