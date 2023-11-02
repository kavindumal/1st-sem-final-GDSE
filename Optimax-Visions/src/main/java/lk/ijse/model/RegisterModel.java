package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.RegisterDto;
import lk.ijse.gmail.Gmailer;

import java.sql.SQLException;
import java.util.Random;

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

    public boolean checkUsernameAvailability() throws SQLException {
        String[][] details= DbConnections.getDetails("user", 3);
        for (int i = 0; i < details.length; i++) {
            if (!details[i][0].equals(registerDto.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public boolean getOtp(String email, int otp) {
        boolean b1 = false;
        if (!email.contains("@")) {
            if (!email.contains("gmail.com")) {
                email = email + "@gmail.com";
            }
        }
        try {
            b1 = Gmailer.setEmailCom(email, otp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b1;
    }

    public boolean checkEmailLong(){
        return !registerDto.getEmailAddress().isEmpty();
    }

    public int generateNewOtp() {
        int otp;
        do {
            Random random = new Random();
            otp = random.nextInt(9999);
            if (otp > 1000) return otp;
        }while (true);
    }
}