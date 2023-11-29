package lk.ijse.model;

import lk.ijse.AesAlgorithm.AesAlgorithm;
import lk.ijse.db.DbConnections;

public class OtpModel {


    public boolean setDetailsToDatabase(String username, String password, String email) {
        AesAlgorithm aes = new AesAlgorithm();
        try {
            return DbConnections.setDetails("INSERT INTO visioncare.user (username, password, email, accountType)\n" +
                    "VALUES ('" + username + "', '" + aes.encrypt(password) + "', '" + email + "','Local');\n" +
                    "\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}