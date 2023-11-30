package lk.ijse.model;

import lk.ijse.db.DbConnections;

import java.sql.SQLException;

public class PrescriptionModel {

    public boolean checkAppointmentId(String text) throws SQLException {
        String[][] appointmentDetails = DbConnections.getDetails("appointment", 8);
        for (int i = 0; i < appointmentDetails.length; i++) {
            if (text.equals(appointmentDetails[i][0])) {
                return true;
            }
        }
        return false;
    }
}
