package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.ForgotDto;
import lk.ijse.gmail.Gmailer;

import java.sql.SQLException;
import java.util.Random;

public class ForgotModel {

    public boolean checkEmailAvailability(ForgotDto forgotDto) throws SQLException {
        String[][] detailsOfUsers = DbConnections.getDetails("user", 3);
        for (String[] detailsOfUser : detailsOfUsers) {
            if (detailsOfUser[2].equals(forgotDto.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean getOtp(String email, int otp) {
        boolean b1 = false;
        if (email.contains("@")){
            int index = email.indexOf("@");
            if (!email.substring(index + 1).equals("gmail.com")){
                return false;
            }
        } else {
            return false;
        }
        try {
            b1 = Gmailer.setEmailCom(email, otp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b1;
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
