package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.OtpVerificationDto;
import lk.ijse.dto.RegisterDto;
import lk.ijse.gmail.Gmailer;

import java.sql.SQLException;
import java.util.Random;

public class OtpVerificationModel {
    public static RegisterDto registerDto;
    public OtpVerificationModel(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }
    int otp;
    OtpVerificationDto otpVerificationDto;
    public OtpVerificationModel(OtpVerificationDto otpVerificationDto){
        this.otpVerificationDto = otpVerificationDto;
    }

    public void sendEmail() throws SQLException {
        String[][] otpAndEmail = DbConnections.getDetails("tempgmailotp",2);

        try {
            Gmailer.setEmailCom(otpAndEmail[0][0], Integer.parseInt(otpAndEmail[0][1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setDetails() throws SQLException {
        DbConnections.setDetails("INSERT INTO visioncare.user (username, password, email)\n" +
                "VALUES ('"+ registerDto.getUsername() +"', '"+ registerDto.getEmailAddress() +"', '"+ registerDto.getPassword() +"');\n" +
                "\n");
    }
}
