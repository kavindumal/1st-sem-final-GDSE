package lk.ijse.model;

import lk.ijse.db.DbConnections;
import lk.ijse.dto.OtpDto;
import lk.ijse.gmail.Gmailer;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Random;

public class OtpModel {


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