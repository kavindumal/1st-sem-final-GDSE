package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.OtpVerificationDto;
import lk.ijse.dto.RegisterDto;
import lk.ijse.gmail.Gmailer;

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

    public void sendEmail() {
        String[][] otpAndEmail = DBConnection.getDetails("tempgmailotp",2);

        try {
            Gmailer.setEmailCom(otpAndEmail[0][0], Integer.parseInt(otpAndEmail[0][1]));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setDetails() {
        DBConnection.setDetails("INSERT INTO visioncare.user (username, password, email)\n" +
                "VALUES ('"+ registerDto.getUsername() +"', '"+ registerDto.getEmailAddress() +"', '"+ registerDto.getPassword() +"');\n" +
                "\n");
    }
}
