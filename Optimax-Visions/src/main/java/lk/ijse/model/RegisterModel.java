package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.RegisterDto;
import org.mindrot.jbcrypt.BCrypt;

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

    public boolean checkUsernameAvailability() {
        String[][] details= DBConnection.getDetails("user", 3);
        for (int i = 0; i < details.length; i++) {
            if (!details[i][0].equals(registerDto.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public void setValues() {
        String otp = BCrypt.hashpw(""+generateNewOtp(), BCrypt.gensalt());
        System.out.println(otp);
        DBConnection.setDetails("INSERT INTO visioncare.tempgmailotp (gmail, otp)\n" +
                "VALUES ("+ registerDto.getEmailAddress()+", "+ otp +");");
    }

    public int generateNewOtp() {
        int otp;
        do {
            Random random = new Random(10000);
            otp = random.nextInt();
        }while (otp > 1000 && otp < 10000);
        return otp;
    }
}