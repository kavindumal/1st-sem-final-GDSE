package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.RegisterDto;

public class RegisterModel {
    public RegisterDto registerDto;
    public RegisterModel(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }

    public boolean checkPasswordLength() {
        return registerDto.getPassword().length() >= 8;
    }

    public boolean checkConfirmPassword() {
        return registerDto.getPassword().equals(registerDto.getConfirmPassword());
    }

    public boolean checkUsernameAvailability() {
        String[][] details= DBConnection.getDetails("user", 3);
        for (int i = 0; i < details.length; i++) {
            if (!details[i][0].equals(registerDto.getUsername())) {
                return true;
            }
        }
        return false;
    }
}