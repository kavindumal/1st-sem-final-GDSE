package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.OtpVerificationDto;
import lk.ijse.dto.RegisterDto;
import lk.ijse.gmail.Gmailer;

import java.sql.SQLException;
import java.util.Random;

public class OtpVerificationModel {
    int otp;
    public static RegisterDto registerDto;

    public OtpVerificationModel(RegisterDto registerDto,int otp) {
        this.registerDto = registerDto;
        this.otp = otp;
    }

    OtpVerificationDto otpVerificationDto;
    public OtpVerificationModel(OtpVerificationDto otpVerificationDto){
        this.otpVerificationDto = otpVerificationDto;
    }

    public void sendEmail() throws SQLException {
        try {
            Gmailer.setEmailCom(registerDto.getEmailAddress(), otp);
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
