package lk.ijse.model;

import lk.ijse.db.DBConnection;
import lk.ijse.dto.OtpVerificationDto;
import lk.ijse.gmail.Gmailer;

import java.util.Random;

public class OtpVerificationModel {
    int otp;
    OtpVerificationDto otpVerificationDto;
    public OtpVerificationModel(OtpVerificationDto otpVerificationDto){
        this.otpVerificationDto = otpVerificationDto;
    }

    public void sendEmail() {
        String[][] otpAndEmail = DBConnection.getDetails("tempgmailotp",2);

        char[] hashedPasswordCharArray = otpAndEmail[0][0].toCharArray();
        char[] hashedPasswordCharArray2 = otpAndEmail[0][1].toCharArray();

        try {
            Gmailer.setEmailCom(new String(hashedPasswordCharArray), Integer.parseInt(new String(hashedPasswordCharArray2)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
