package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.OtpDto;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class OtpModel {
    public boolean verifyOto(OtpDto otpDto, int otp){
        int num1 = Integer.parseInt(otpDto.getOtpOneText());
        int num2 = Integer.parseInt(otpDto.getOtpTwoText());
        int num3 = Integer.parseInt(otpDto.getOtpThreeText());
        int num4 = Integer.parseInt(otpDto.getOtpFourText());
        int total = num1 * 1000 + num2 * 100 + num3 * 10 + num4;

        return total == otp;
    }

    public boolean setDetailsToDatabase(String username, String password, String email) {
        try {
            return DbConnections.setDetails("INSERT INTO visioncare.user (username, password, email)\n" +
                    "VALUES ('" + username + "', '" + BCrypt.hashpw(password, BCrypt.gensalt()) + "', '" + email + "');\n" +
                    "\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
